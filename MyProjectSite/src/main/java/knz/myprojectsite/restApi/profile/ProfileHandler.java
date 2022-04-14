package knz.myprojectsite.restApi.profile;

import knz.myprojectsite.database.model.Personality;
import knz.myprojectsite.database.model.User;
import knz.myprojectsite.database.repository.UserRepository;
import knz.myprojectsite.restApi.identyfication.IAuthenticationJWT;
import knz.myprojectsite.restApi.identyfication.JWTPayload;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
public class ProfileHandler {

    final IAuthenticationJWT iAuthenticateJWT;
    final UserRepository userRepository;

    @GetMapping("/profile/my")
    public ProfileInfoResponse fillProfileInfo(HttpServletRequest request){
        JWTPayload jwtPayload   = iAuthenticateJWT.checkAuthentication(request);
        if (jwtPayload == null)
            return new ProfileInfoResponse("Unauthorized", "401");

        User user = userRepository.findUserByEmail(jwtPayload.getEmail());
        if (user == null)
            return new ProfileInfoResponse("Unauthorized", "401");

        Personality personality = user.getPersonality();

        return new ProfileInfoResponse(personality.getFirst_name(), personality.getSecond_name()
                , personality.getPatronymic(), personality.getNickname(), personality.getBirthdate()
                , user.getGrade().toString());
    }
}
