package com.example.bankingsystem.spring;

import com.example.bankingsystem.data.client.ClientStorage;
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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    ClientService clientService;

    @Autowired
    JuridicalPersonService juridicalPersonService;

    @Autowired
    DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(clientService);
        auth.setPasswordEncoder(bCryptPasswordEncoder());
        return auth;
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       /* http
                .authorizeRequests()
                //Доступ только для не зарегистрированных пользователей
                /*.antMatchers("/client/registration/**").not().fullyAuthenticated()
                .antMatchers("/person/registration/**").not().fullyAuthenticated()
                .antMatchers("/page/indexUnauthorized/**").authenticated()

                //Доступ для пользователей с ролью Администратор, Клиент, Юридическое лицо
                //.antMatchers("/admin/**").hasRole("ADMIN")
                //.antMatchers("/client/**").hasRole("CLIENT")
                //.antMatchers("/person/**").hasRole("JURIDICAL_PERSON")

                //Доступ для всех
                .antMatchers("/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .anyRequest().authenticated()

                .and()
                    .formLogin()
                    .loginPage("/page/authorization")
                    .permitAll()
                    .defaultSuccessUrl("/page/indexAuthorized")
                    .failureUrl("/page/login-error")
                .and()
                    .logout()
                    .permitAll()
                    .logoutSuccessUrl("/");*/

        /*http.authorizeRequests()
                .antMatchers("/**")
                .permitAll()
                .anyRequest().authenticated();

        http
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/", "/home").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();*/

        http
                .authorizeRequests()
                    .antMatchers("/client/registration/**").not().fullyAuthenticated()
                    .antMatchers("/person/registration/**").not().fullyAuthenticated()
                    .antMatchers("/css/**").permitAll()
                    //.antMatchers("/page/indexUnauthorized/**").authenticated()


                    //.antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/client/**").hasRole("CLIENT")
                    .antMatchers("/person/**").hasRole("JURIDICAL_PERSON")

                /*.antMatchers("/").permitAll()
                    .antMatchers("/client/registration").not().fullyAuthenticated()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/client/**").hasRole("CLIENT")
                    .anyRequest().authenticated()*/


                .and()
                    .formLogin()
                    .loginPage("/page/authorization")
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .permitAll();
    }
    /*@Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withUserDetails()
                        .username("user")
                        .password("123")
                        .roles("USER")
                        .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())

    }*/
}


    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("clientName").password("password").roles("CLIENT")
             .and()
                .withUser("personName").password("password").roles("JURIDICAL_PERSON");
    }*/
