package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityConfig {

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    private CustomAccessDeniedException customeAccessDeniedHandler;
    
    @Bean
    @Order(1)
    public SecurityFilterChain customSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/secureContent")
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .defaultSuccessUrl("/secureContent")
                .permitAll()
            )
            .logout(logout -> logout
                .permitAll()
            );
            // .exceptionHandling(ex -> ex
            //     .authenticationEntryPoint(customAuthenticationEntryPoint)
            //     .accessDeniedHandler(customeAccessDeniedHandler)
            // );
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain anotherSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/confidentialData")
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .defaultSuccessUrl("/confidentialData")
                .permitAll()
            )
            .logout(logout -> logout
                .permitAll()
            );
            // .exceptionHandling(ex -> ex
            //     .authenticationEntryPoint(customAuthenticationEntryPoint)
            //     .accessDeniedHandler(customeAccessDeniedHandler)
            // );
        return http.build();
    }

    @Order(3)
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/login", "/logout", "/welcome", "/")
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll()
            )
            .formLogin(fl -> fl.defaultSuccessUrl("/welcome"));
            // .exceptionHandling(ex -> ex
            //     .authenticationEntryPoint(customAuthenticationEntryPoint)
            //     .accessDeniedHandler(customeAccessDeniedHandler)
            // );
        return http.build();
    }

    @Bean
    public UserDetailsService customUserDetails() {
        UserDetails user = User.withUsername("admin")
                               .password("{noop}admin123")
                               .roles("ADMIN", "USER")
                               .build();
        
        UserDetails user2 = User.withUsername("charan")
                                .password("{noop}charan123")   
                                .roles("USER")
                                .build(); 
        return new InMemoryUserDetailsManager(user, user2);
    }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }
    
}
