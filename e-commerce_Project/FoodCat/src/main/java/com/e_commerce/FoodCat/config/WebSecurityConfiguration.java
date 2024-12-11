//package com.e_commerce.FoodCat.config;
//
//import com.e_commerce.FoodCat.filter.JwtRequestFilter;
//import com.e_commerce.FoodCat.services.jwt.UserDetailsServiceImpl;
//import com.e_commerce.FoodCat.utils.JwtUtils;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.HttpStatusEntryPoint;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
// class WebSecurityConfiguration {
//
////    private final JwtRequestFilter jwtRequestFilter;
////    private final UserDetailsServiceImpl userDetailsService;
////    private final JwtUtils jwtUtils;
////    private final CustomAccessDeniedHandler accessDeniedHandler;
////
////    public WebSecurityConfiguration(JwtRequestFilter jwtRequestFilter,
////                                    UserDetailsServiceImpl userDetailsService,
////                                    JwtUtils jwtUtils,
////                                    CustomAccessDeniedHandler accessDeniedHandler) {
////        this.jwtRequestFilter = jwtRequestFilter;
////        this.userDetailsService = userDetailsService;
////        this.jwtUtils = jwtUtils;
////        this.accessDeniedHandler = accessDeniedHandler;
//
//    }
//
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////
////                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
////                .authorizeHttpRequests(authorizeRequests ->
////                        authorizeRequests
////                                .requestMatchers("/authenticate", "/sign-up", "/order/**").permitAll()
////                                .requestMatchers("/api/**").authenticated()
////                                .anyRequest().denyAll() // Optionally, you can deny all other requests
////                )
////                .exceptionHandling(exceptionHandling ->
////                        exceptionHandling
////                                .accessDeniedHandler(accessDeniedHandler)
////                                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
////                )
////                .sessionManagement(sessionManagement ->
////                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                )
////                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
////
////
////        return http.build();
////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////
////    @Bean
////    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
////        return config.getAuthenticationManager();
////    }
////}
