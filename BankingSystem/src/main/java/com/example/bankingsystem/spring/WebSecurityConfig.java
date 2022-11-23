package com.example.bankingsystem.spring;

import com.example.bankingsystem.domain.JuridicalPerson.JuridicalPersonService;
import com.example.bankingsystem.domain.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    ClientService clientService;

    @Autowired
    JuridicalPersonService juridicalPersonService;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //Доступ только для не зарегистрированных пользователей
                .antMatchers("/client/registration/**").not().fullyAuthenticated()
                .antMatchers("/person/registration/**").not().fullyAuthenticated()

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
                    .defaultSuccessUrl("/page/indexAuthorized")
                    .failureUrl("/page/login-error.html")
                .and()
                    .logout()
                    .permitAll()
                    .logoutSuccessUrl("/");

        /*http.authorizeRequests()
                .antMatchers("/**")
                .permitAll()
                .anyRequest().authenticated();*/
    }
}
