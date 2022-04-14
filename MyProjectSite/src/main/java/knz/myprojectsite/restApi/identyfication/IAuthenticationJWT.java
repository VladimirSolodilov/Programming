package knz.myprojectsite.restApi.identyfication;

import javax.servlet.http.HttpServletRequest;

public interface IAuthenticationJWT {
    JWTPayload checkAuthentication(HttpServletRequest request);
}
