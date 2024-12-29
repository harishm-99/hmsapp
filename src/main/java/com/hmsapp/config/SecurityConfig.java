package com.hmsapp.config;

import com.hmsapp.service.JWTService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;

@Configuration
public class SecurityConfig {

    private JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter){
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    )throws Exception{

        //h(cd)2
        http.csrf().disable().cors().disable();

        //filter applied to requested urls wo tokens. i.e. closed urls not open urls
        //runs the jwtFilter Class before the builtin AuthorizationFilter class
        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);

        //whenever person enters this url. This url is open to access and does not require jwt token to access this.
        //haap
//        http.authorizeHttpRequests().anyRequest().permitAll();

        http.authorizeHttpRequests()
                // anyone accessing this url does not require token. Not authenticated urls/ search urls i.e. hotel/bus searching
                .requestMatchers("/api/auth/**",
                        "/api/v1/property/**",
                        "api/v1/city/**",
                        "api/v1/country/**",
                        "/api/files/**")
                .permitAll()

                .requestMatchers("/api/v1/property/addProperty","/api/v1/property/addPropertyPhotos")
                .hasRole("OWNER")//dummyrole - for adding single role

                .requestMatchers("/api/v1/property/deleteProperty", "/api/v1/property/updateProperty")
                .hasAnyRole("OWNER","ADMIN")

                .requestMatchers("/api/auth/blog/sign-up")
                .hasRole("ADMIN")

                .anyRequest().authenticated();//rest requires authentication

        return http.build();
    }
}
