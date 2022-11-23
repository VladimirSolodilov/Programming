package com.example.bankingsystem.spring;

import com.example.bankingsystem.domain.JuridicalPerson.JuridicalPersonService;
import com.example.bankingsystem.domain.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    ClientService clientService;

    @Autowired
    JuridicalPersonService juridicalPersonService;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(clientService);
        auth.setPasswordEncoder(bCryptPasswordEncoder());
        return auth;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.csrf().disable()
                .authorizeRequests()
                //Доступ только для не зарегистрированных пользователей
                .antMatchers("/client/registration/**").not().fullyAuthenticated()
                .antMatchers("/person/registration/**").not().fullyAuthenticated()
                .antMatchers("/page/indexUnathorized/**").authenticated()

                //Доступ для пользователей с ролью Администратор, Клиент, Юридическое лицо
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/client/**").hasRole("CLIENT")
                .antMatchers("/person/**").hasRole("JURIDICAL_PERSON")

                //Доступ для всех
                .antMatchers("/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .anyRequest().authenticated()

                .and()
                    .formLogin()
                    .loginPage("/page/authorization")
                    .loginProcessingUrl("/page/indexUnauthorized")
                    .usernameParameter("login")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/page/indexAuthorized")
                    .failureUrl("/page/login-error")
                .and()
                    .logout()
                    .permitAll()
                    .logoutSuccessUrl("/");

        /*http.authorizeRequests()
                .antMatchers("/**")
                .permitAll()
                .anyRequest().authenticated();*/
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("clientName").password("password").roles("CLIENT")
             .and()
                .withUser("personName").password("password").roles("JURIDICAL_PERSON");
    }

}
