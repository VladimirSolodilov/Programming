package com.example.myprojectsite.spring.identification;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignInRequest {
    @NonNull private String email;
    @NonNull private String password;
}
