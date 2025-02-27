package com.springboot.secure_notes.security;

import com.springboot.secure_notes.models.Note;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) ->
                requests.anyRequest().authenticated());
        http.csrf(csrf->csrf.disable());
        //http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());

//      This step is to make the session stateless i.e. no data is saved on the client side.
//        http.sessionManagement(session->
//                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager=new InMemoryUserDetailsManager();
        if(!manager.userExists("user1")){
            manager.createUser(
                    User.withUsername("user1")
                            .password("{noop}password1")
                            .roles("USER")
                            .build()
            );
        }
        if(!manager.userExists("admin")){
            manager.createUser(
                    User.withUsername("admin")
                            .password("{noop}adminPass")
                            .roles("ADMIN")
                            .build()
            );
        }
        return manager;
    }
}
