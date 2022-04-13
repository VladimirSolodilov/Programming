package com.example.myprojectsite.spring.identification;

import javax.servlet.http.HttpServletRequest;
public interface IAuthenticationJWT {
    JWTPayload checkAuthentication(HttpServletRequest request);
}