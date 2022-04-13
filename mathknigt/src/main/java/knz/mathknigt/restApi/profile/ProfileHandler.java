package knz.mathknigt.restApi.profile;

import knz.mathknigt.database.model.ImpactSet;
import knz.mathknigt.database.model.Personality;
import knz.mathknigt.database.model.User;
import knz.mathknigt.database.repository.ImpactSetRepository;
import knz.mathknigt.database.repository.UserRepository;
import knz.mathknigt.restApi.identyfication.IAuthenticationJWT;
import knz.mathknigt.restApi.identyfication.JWTPayload;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
public class ProfileHandler {

    final IAuthenticationJWT    iAuthenticateJWT;
    final UserRepository        userRepository;
    final ImpactSetRepository   impactSetRepository;

    @GetMapping("/profile/my")
    public ProfileInfoResponse fillProfileInfo(HttpServletRequest request){
        JWTPayload jwtPayload   = iAuthenticateJWT.checkAuthentication(request);
        if (jwtPayload == null)
            return new ProfileInfoResponse("Unauthorized", "401");

        User user = userRepository.findUserByEmail(jwtPayload.getEmail());
        if (user == null)
            return new ProfileInfoResponse("Unauthorized", "401");

        Personality personality = user.getPersonality();
        ImpactSet impactSet     = user.getImpact_set();

        return new ProfileInfoResponse(personality.getFirst_name(), personality.getSecond_name()
                , personality.getPatronymic(), personality.getNickname(), personality.getBirthdate()
                , user.getGrade().toString()
                , impactSet.getPhysical_value().toString(), impactSet.getMental_value().toString());
    }

    @PostMapping("/impactSet/set/physical")
    public void setPhysicalImpact(@RequestBody SetImpactRequest setImpactRequest, HttpServletRequest httpServletRequest){
        try {
            User user = userRepository.findUserByEmail(iAuthenticateJWT.checkAuthentication(httpServletRequest)
                    .getEmail());
            if (user == null) throw new Exception();
            ImpactSet impactSet = user.getImpact_set();
            impactSet.setPhysical_value(Long.valueOf(setImpactRequest.getNewValue()));

            impactSetRepository.save(impactSet);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("User dont exists");
        }
    }

    @PostMapping("/impactSet/set/mental")
    public void setMentalImpact(@RequestBody SetImpactRequest setImpactRequest, HttpServletRequest httpServletRequest){
        try {
            User user = userRepository.findUserByEmail(iAuthenticateJWT.checkAuthentication(httpServletRequest)
                    .getEmail());
            if (user == null) throw new Exception();
            ImpactSet impactSet = user.getImpact_set();
            impactSet.setMental_value(Long.valueOf(setImpactRequest.getNewValue()));

            impactSetRepository.save(impactSet);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("User dont exists");
        }
    }
}
