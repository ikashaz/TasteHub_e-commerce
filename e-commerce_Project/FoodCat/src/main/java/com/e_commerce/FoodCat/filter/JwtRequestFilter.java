//////package com.e_commerce.FoodCat.filter;
//////
//////import com.e_commerce.FoodCat.services.jwt.UserDetailsServiceImpl;
//////import com.e_commerce.FoodCat.utils.JwtUtils;
//////import jakarta.servlet.FilterChain;
//////import jakarta.servlet.ServletException;
//////import jakarta.servlet.http.HttpServletRequest;
//////import jakarta.servlet.http.HttpServletResponse;
//////import lombok.RequiredArgsConstructor;
//////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//////import org.springframework.security.core.context.SecurityContextHolder;
//////import org.springframework.security.core.userdetails.UserDetails;
//////import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//////import org.springframework.stereotype.Component;
//////import org.springframework.web.filter.OncePerRequestFilter;
//////import java.io.IOException;
//////
//////@Component
//////@RequiredArgsConstructor
//////public class JwtRequest extends OncePerRequestFilter {
//////
//////    private final UserDetailsServiceImpl userDetailsService;
//////
//////    private final JwtUtils jwtUtils;
//////
//////    @Override
//////    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//////        String authHeader= request.getHeader("Authorization");
//////        String token=null;
//////        String username=null;
//////
//////        if(authHeader != null && SecurityContextHolder.getContext().getAuthentication()==null){
//////            UserDetails userDetails= userDetailsService.loadUserByUsername(username);
//////
//////            if(jwtUtils.validateToken(token,userDetails)){
//////                UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(userDetails,null);
//////                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//////                SecurityContextHolder.getContext().setAuthentication(authToken);
//////            }
//////        }
//////
//////        filterChain.doFilter(request,response);
//////    }
//////}
////package com.e_commerce.FoodCat.filter;
////
////import com.e_commerce.FoodCat.services.jwt.UserDetailsServiceImpl;
////import com.e_commerce.FoodCat.utils.JwtUtils;
////import jakarta.servlet.FilterChain;
////import jakarta.servlet.ServletException;
////import jakarta.servlet.http.HttpServletRequest;
////import jakarta.servlet.http.HttpServletResponse;
////import lombok.RequiredArgsConstructor;
////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
////import org.springframework.security.core.context.SecurityContextHolder;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
////import org.springframework.stereotype.Component;
////import org.springframework.web.filter.OncePerRequestFilter;
////
////import java.io.IOException;
////@Component
////@RequiredArgsConstructor
////public class JwtRequestFilter extends OncePerRequestFilter {
////
////    private final UserDetailsServiceImpl userDetailsService;
////    private final JwtUtils jwtUtils;
////
////    @Override
//////    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//////            throws ServletException, IOException {
//////        String authHeader = request.getHeader("Authorization");
//////        String token = null;
//////        String username = null;
//////
//////        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//////            token = authHeader.substring(7); // Extract token
//////            username = jwtUtils.getUsernameFromToken(token);
//////
//////            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//////                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//////
//////                if (jwtUtils.validateToken(token, userDetails)) {
//////                    UsernamePasswordAuthenticationToken authToken =
//////                            new UsernamePasswordAuthenticationToken(userDetails,
//////                             null, userDetails.getAuthorities());
//////                    authToken.setDetails(new WebAuthenticationDetailsSource()
//////                            .buildDetails(request));
//////                    SecurityContextHolder.getContext().setAuthentication(authToken);
//////                }
//////            }
//////        }
//////
//////        filterChain.doFilter(request, response);
//////    }
////    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
////            throws ServletException, IOException {
////        String authHeader = request.getHeader("Authorization");
////        String token = null;
////        String username = null;
////
////        try {
////            if (authHeader != null && authHeader.startsWith("Bearer ")) {
////                token = authHeader.substring(7); // Extract token
////                username = jwtUtils.getUsernameFromToken(token);
////
////                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
////                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
////
////                    if (jwtUtils.validateToken(token, userDetails)) {
////                        UsernamePasswordAuthenticationToken authToken =
////                                new UsernamePasswordAuthenticationToken(userDetails,
////                                        null, userDetails.getAuthorities());
////                        authToken.setDetails(new WebAuthenticationDetailsSource()
////                                .buildDetails(request));
////                        SecurityContextHolder.getContext().setAuthentication(authToken);
////                    } else {
////                        System.out.println("Token is invalid");
////                    }
////                } else {
////                    System.out.println("Username is null or authentication already set");
////                }
////            } else {
////                System.out.println("Authorization header is missing or does not start with Bearer");
////            }
////        } catch (Exception e) {
////            System.out.println("Exception in doFilterInternal: " + e.getMessage());
////            e.printStackTrace();
////        }
////
////        filterChain.doFilter(request, response);
////    }
////}
//
//package com.e_commerce.FoodCat.filter;
//
//import com.e_commerce.FoodCat.utils.JwtUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//@Component
//public class JwtRequestFilter extends OncePerRequestFilter {
//
//    private final JwtUtils jwtUtil;
//    private final UserDetailsService userDetailsService;
//
//    @Autowired
//    public JwtRequestFilter(JwtUtils jwtUtil, UserDetailsService userDetailsService) {
//        this.jwtUtil = jwtUtil;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//        final String authorizationHeader = request.getHeader("Authorization");
//
//        String username = null;
//        String jwt = null;
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            jwt = authorizationHeader.substring(7);
//            username = jwtUtil.extractUsername(jwt);
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            try {
//                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//                if (jwtUtil.validateToken(jwt, userDetails.getUsername())) {
//                    UsernamePasswordAuthenticationToken authToken =
//                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                    SecurityContextHolder.getContext().setAuthentication(authToken);
//                }
//            } catch (Exception e) {
//                // Log the error
//                System.err.println("Failed to set authentication: " + e.getMessage());
//                e.printStackTrace(); // Consider using a logging framework instead
//            }
//        }
//
//        chain.doFilter(request, response);
//    }
//}
//
//
