package com.e_commerce.FoodCat.Controller;


import com.e_commerce.FoodCat.dto.AuthResponse;
import com.e_commerce.FoodCat.dto.AuthenticationRequest;
import com.e_commerce.FoodCat.dto.SignupRequest;
import com.e_commerce.FoodCat.dto.UserDto;
import com.e_commerce.FoodCat.entity.User;
import com.e_commerce.FoodCat.repository.UserRepository;
import com.e_commerce.FoodCat.services.auth.AuthService;
//import com.e_commerce.FoodCat.utils.JwtUtils;
import com.e_commerce.FoodCat.utils.JwtUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {


    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;

    private static final String TOKEN_PREFIX = "Bearer";

    private static final String HEADER_STRING = "Authorization";

    private final AuthService authService;



//    // create login API method
//    @GetMapping("/generate-token")
//    public String generateToken(@RequestParam UserDto username) {
//        String us= username.getEmail();
//        return jwtUtils.generateToken(us);
//    }

//    @PostMapping("/authenticate")
//    public void createAuthencationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response)
//            throws IOException, JSONException {
////
////        try{
////            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
////                    authenticationRequest.getPassword()));
////        } catch (BadCredentialsException e){
////            throw new BadCredentialsException("Incorrect username or password.");
////
////        }
////
////        //get user details
////        final UserDetails userDetails= userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
////
////        Optional<User> optionalUser= userRepository.findFirstByEmail(userDetails.getUsername());
////
////        //call jwt to generate the token
////        final String jwt=jwtUtils.generatorToken(userDetails.getUsername());
////
////        //if present show the details
////        if(optionalUser.isPresent()){
////            response.getWriter().write(new JSONObject()
////                    .put("userId",optionalUser.get().getId())
////                    .put("role",optionalUser.get().getRole()) //this role is from enums that we add at User class
////                    .toString()
////                    );
////            response.addHeader("Access-Control-Expose-Headers", "Authorization");
////            response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");
////            response.addHeader(HEADER_STRING,TOKEN_PREFIX + jwt);
////
////        }
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            authenticationRequest.getEmail(),
//                            authenticationRequest.getPassword()
//                    )
//            );
//        } catch (BadCredentialsException e) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Incorrect username or password.");
//            return;
//        }
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
//
////        String jwt = jwtUtils.generateToken(userDetails.getUsername()); //send to jwtutil
////        System.out.println("Jwt: " + jwt);
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//
//        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
//        if (optionalUser.isPresent()) {
//            JSONObject jsonResponse = new JSONObject()
//                    .put("userId", optionalUser.get().getId())
//                    .put("role", optionalUser.get().getRole());
//
//            response.addHeader("Authorization", "Bearer " + jwt);
//            response.getWriter().write(jsonResponse.toString());
//        } else {
//            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found.");
//        }
//    }


    @PostMapping("api/sign-up")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
        if (authService.hasUserWithEmail(signupRequest.getEmail())) {
            return new ResponseEntity<>("user already exist", HttpStatus.NOT_ACCEPTABLE);
        }

        UserDto userDto = authService.createUser(signupRequest);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

////login without jwt
@CrossOrigin(origins = "http://localhost:4200")
@PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody UserDto loginRequest) {

        try {
            // Authenticate the user using the provided username and password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            // Set the authentication in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Optionally, you can generate a JWT token here if you're using JWT
            // String token = jwtProvider.generateToken(authentication);

            // Return a response with a success message and, optionally, the token
            // Return user role for redirection
            // Return user role for redirection (if applicable)
//            String role = authentication.getAuthorities().stream()
//                    .findFirst()
//                    .map(GrantedAuthority::getAuthority)
//                    .orElse("CUSTOMER"); // Default role if none found
//
//            // Optionally include user ID or other details in response
//            return ResponseEntity.ok(new UserDto(role));

            //return ResponseEntity.ok(Collections.singletonMap("message", "Login successful!"));
            // Get the authenticated user
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Fetch user information from the repository using the username
            Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                UserDto userDto = new UserDto();
                userDto.setId(user.getId());
                userDto.setEmail(user.getEmail());
                userDto.setUsername(user.getUsername());
                userDto.setRole(user.getRole().name()); // Get the role as a string

                return ResponseEntity.ok(userDto);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "User not found."));
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "Invalid username or password."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "An error occurred during login."));
        }
    }


//    @PostMapping("api/login") //for testing
//    public String login(@RequestBody User user) {
//        if ("testUser".equals(user.getUsername()) && "testPass".equals(user.getPassword())) {
//            return "Login Successful!";
//        }
//        return "Login Failed!";
//    }

//    @PostMapping("api/login")
//    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
//        try {
//            // Authenticate the user
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            authenticationRequest.getEmail(),
//                            authenticationRequest.getPassword()
//                    )
//            );
//
//            // Load user details
//            UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
//            Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
//
//            // Generate JWT token
//            String token = jwtUtils.generateToken(userDetails.getUsername());
//
//            // If user is present, return the user ID and token
//            if (optionalUser.isPresent()) {
//                User user = optionalUser.get();
//                return ResponseEntity.ok(new AuthResponse(token, user.getId()));
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//            }
//
//        } catch (BadCredentialsException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
//        }
//    }

//    @PostMapping("api/login")
//    public ResponseEntity<?> login(@RequestBody UserDto authenticationRequest) {
//        try {
//            // Authenticate the user
//            User user = authService.authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
//
//            if (user != null) {
//                // Generate JWT token
//                String token = jwtUtils.generateToken(user.getEmail());
//                return ResponseEntity.ok(new AuthResponse(token, user.getId())); // Return token and user ID
//            } else {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//            }
//
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
//        }
//    }

}
