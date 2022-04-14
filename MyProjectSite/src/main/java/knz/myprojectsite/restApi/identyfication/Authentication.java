package knz.myprojectsite.restApi.identyfication;

import knz.myprojectsite.database.model.Personality;
import knz.myprojectsite.database.model.Role;
import knz.myprojectsite.database.model.Salt;
import knz.myprojectsite.database.model.User;
import knz.myprojectsite.database.repository.PersonalityRepository;
import knz.myprojectsite.database.repository.RoleRepository;
import knz.myprojectsite.database.repository.SaltRepository;
import knz.myprojectsite.database.repository.UserRepository;
import knz.myprojectsite.restApi.DefaultResponse;
import lombok.NonNull;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static knz.myprojectsite.configs.ConfigAuth.authSecret;

@RestController
public class Authentication implements IAuthenticationJWT{

    @NonNull
    private static final String authCookieName = "authentication_token";

    final SaltRepository saltRepository;
    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final PersonalityRepository personalityRepository;

    public Authentication(SaltRepository saltRepository, UserRepository userRepository, RoleRepository roleRepository
                            , PersonalityRepository personalityRepository){
        this.saltRepository         = saltRepository;
        this.userRepository         = userRepository;
        this.roleRepository         = roleRepository;
        this.personalityRepository  = personalityRepository;
    }


    public JWTPayload checkAuthentication(HttpServletRequest request) {
        if (request.getCookies() == null)
            return null;
        for (Cookie cookie : request.getCookies())
            if (cookie.getName().equals(authCookieName))
                return JWTPayload.valueOf(cookie.getValue(), authSecret);
        return null;
    }

    @PostMapping("/register")
    public DefaultResponse sign_up(@RequestBody(required = false) SignUpRequest request){

        String  salt            = RandomString.make();
        String  password_hash   = DigestUtils.sha1Hex(String.format("%s%s", request.getPassword(), salt));

        try{
            Long id_salt = saltRepository.save(new Salt(salt)).getId();
            try {
                Long id_personality = personalityRepository.save(new Personality(
                        request.getFirst_name(), request.getSecond_name(), request.getPatronymic()
                        , request.getNickname(), request.getBirthdate())).getId();
                try {
                    User tempUser = userRepository.findUserByEmail(request.getEmail());
                    if (tempUser != null) throw new IllegalArgumentException();
                    if (roleRepository.count() == 0) {
                        roleRepository.save(new Role("User"));
                        roleRepository.save(new Role("Administrator"));
                    }
                    userRepository.save(new User(request.getEmail(), password_hash
                            , saltRepository.findById(id_salt).orElseThrow()
                            , roleRepository.findRoleByName("User")
                            , personalityRepository.findById(id_personality).orElseThrow()));
                } catch (Exception exception) {
                    saltRepository.deleteById(id_salt);
                    personalityRepository.deleteById(id_personality);
                    return new DefaultResponse("This user already exists", "400");
                }
            }catch (Exception exception){
                saltRepository.deleteById(id_salt);
                return new DefaultResponse("Personality creating fault", "500");
            }
        }catch (Exception exception){
            return new DefaultResponse("Salt creating fault", "500");
        }
        System.out.println("created");
        return new DefaultResponse("Completed", "200");
    }

    @PostMapping("/authenticate")
    public DefaultResponse sign_in(@RequestBody SignInRequest request, HttpServletResponse response){
        User    user;
        try {
            user                = userRepository.findUserByEmail(request.getEmail());
            if(user == null) throw new NullPointerException();
        }catch (Exception exception){
            exception.printStackTrace();
            return new DefaultResponse("User does not exist", "400");
        }

        Salt salt            = saltRepository.findById(user.getSalt().getId()).orElse(null);
        if(salt==null)
            return new DefaultResponse("Validation is failed", "500");

        String  password_hash   = DigestUtils.sha1Hex(String.format("%s%s", request.getPassword(), salt.getSalt()));
        if(!password_hash.equals(user.getPassword()))
            return new DefaultResponse("Invalid password", "400");

        String jwtString        = new JWTPayload(user.getId(), user.getEmail()).toJWT(authSecret);
        if (jwtString == null)
            return new DefaultResponse("Can not to authorise", "500");

        Cookie cookie = new Cookie(authCookieName, jwtString);
        cookie.setPath("/");
        response.addCookie(cookie);

        return new DefaultResponse("Successful signed in", "200");
    }

    @GetMapping("/signouts")
    public DefaultResponse sign_out(HttpServletResponse response){
        Cookie cookie = new Cookie(authCookieName, "0");
        cookie.setPath("/");
        response.addCookie(cookie);

        return new DefaultResponse("Successful signed out", "200");
    }

    @GetMapping("/gotomain")
    public DefaultResponse goToMain(HttpServletResponse response) {
        Cookie cookie = new Cookie(authCookieName, "0");
        cookie.setPath("/main_authorized");
        response.addCookie(cookie);

        return new DefaultResponse("Successful main", "200");
    }
}
