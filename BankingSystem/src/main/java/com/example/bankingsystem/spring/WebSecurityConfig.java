package com.example.bankingsystem.spring;

import com.example.bankingsystem.data.role.RoleStorage;
import com.example.bankingsystem.domain.JuridicalPerson.JuridicalPersonService;
import com.example.bankingsystem.domain.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    RoleStorage roleStorage;

    @Autowired
    ClientService clientService;

    @Autowired
    JuridicalPersonService juridicalPersonService;

    @Autowired
    DataSource dataSource;
    @Bean
    protected BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .and()
                    .formLogin()
                    .loginPage("/signIn")
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .permitAll();
        return http.build();
        /*
        http
                .headers().xssProtection();
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/").permitAll()
                .antMatchers("/signIn").permitAll()

                .antMatchers("/authorized/client/**").hasAuthority("CLIENT")
                .antMatchers("/authorized/person/**").hasAuthority("JURIDICAL_PERSON")
                .antMatchers("/authorized/admin/**").hasAuthority("ADMIN")

                .and()
                    .formLogin()
                    .loginPage("/signIn")
                    .defaultSuccessUrl("/authorized")
                    //.failureUrl("/signIn/error")
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .permitAll();
        return http.build();*/
    }
}
