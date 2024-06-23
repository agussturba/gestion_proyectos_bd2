package com.grupo.bd2.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String MANAGER = "MANAGER";

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserDetails user1 = User.withUsername("user1")
                .password(encoder.encode("12345"))
                .roles("EMPLOYEE")
                .build();
        UserDetails user2 = User.withUsername("user2")
                .password(encoder.encode("12345"))
                .roles("EMPLOYEE")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("12345"))
                .roles(MANAGER)
                .build();
        return new InMemoryUserDetailsManager(user1, user2, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/employee").hasRole(MANAGER)
                                .requestMatchers("/api/project").hasRole(MANAGER)
                                .requestMatchers("/api/comment").permitAll()
                                .requestMatchers("/api/task").permitAll()
                                .requestMatchers("/api/resource").permitAll()
                                .requestMatchers("/api/progress").permitAll()
                                .anyRequest().authenticated()
                )
                .csrf().disable()
                .httpBasic();
        return http.build();
    }
}