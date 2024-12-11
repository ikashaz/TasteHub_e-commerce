package com.e_commerce.FoodCat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())) // Configure CSRF
//                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/api/products/**").permitAll() // Permit access to your API
//                        .anyRequest().authenticated() // Other requests need authentication
//                );


    //most stable

     //the real one
//      http
//                .csrf(csrf -> csrf.disable()) // Disable CSRF for testing
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().permitAll() // Allow all requests
//                );
//
//        return http.build();
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for testing (not recommended in production)
                .authorizeRequests(auth -> auth
                        .anyRequest().permitAll()  // Allow all requests (not recommended in production)
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")  // Define the logout URL (default is /logout)
                        .logoutSuccessUrl("/login")  // Redirect to login page after logout
                        .invalidateHttpSession(true)  // Invalidate the session
                        .clearAuthentication(true)  // Clear authentication details
                        .deleteCookies("JSESSIONID")  // Delete the session cookie (optional)
                );
        return http.build();
    }



//        http
//                .csrf(csrf -> csrf.disable()) // Disable CSRF for testing
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("api/login","api/sign-up","/api/products").permitAll() // Allow access to the login endpoint
//                        .anyRequest().authenticated() // Require authentication for all other requests
//                );
//


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(authorizeRequests -> authorizeRequests
//                        .anyRequest().permitAll() // All other requests require authentication
//                )
//                .sessionManagement(sessionManagement ->
//                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Use stateless sessions
//                );
//
//        return http.build();
//    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())  // Disable CSRF if you're not using it
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/api/login", "/api/sign-up").permitAll()  // Allow login and sign-up
//                                .requestMatchers("/api/cart/**").authenticated()  // Secure the cart API
//                                .anyRequest().permitAll()  // Allow all other requests
//                )
//                .formLogin(formLogin ->
//                        formLogin
//                                .loginPage("/login")  // Custom login page URL
//                                .loginProcessingUrl("/api/login")  // URL to process login
//                                .defaultSuccessUrl("/cart", true)  // Redirect after login
//                                .permitAll()  // Allow public access to the login page
//                )
//                .logout(logout ->
//                        logout
//                                .logoutUrl("/logout")  // Custom logout URL
//                                .permitAll()  // Allow public access to logout
//                )
//                .sessionManagement(session ->
//                        session
//                                .maximumSessions(1)  // Allow only one session per user
//                                .expiredUrl("/login?expired")  // Redirect to login page if session expired
//                );
//
//        return http.build();
//    }
}


