package com.e_commerce.FoodCat.services.auth;
//
import com.e_commerce.FoodCat.dto.SignupRequest;
import com.e_commerce.FoodCat.dto.UserDto;
import com.e_commerce.FoodCat.entity.User;
import com.e_commerce.FoodCat.enums.UserRole;
import com.e_commerce.FoodCat.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//
//@Service
//public class AuthServiceImpl implements AuthService{
//
//    @Autowired //when need to use database
//    private UserRepository userRepository;
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
////    private final PasswordEncoder passwordEncoder;
////
////    @Autowired
////    public AuthServiceImpl(PasswordEncoder passwordEncoder) {
////        this.passwordEncoder = passwordEncoder;
////    }
////
////    public void registerUser(String rawPassword) {
////        String encodedPassword = passwordEncoder.encode(rawPassword);
////        // Save the encoded password to your database
////    }
//
//    public UserDto createUser(SignupRequest signupRequest){
//        User user=new User();
//
//        user.setEmail(signupRequest.getEmail());
//        user.setName(signupRequest.getName());
//        user.setUsername(signupRequest.getUsername());
//        //encrypt the password
//        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
//        //user.setPassword(signupRequest.getPassword());
//        //since this for customer then use customer
//        user.setRole(UserRole.CUSTOMER);
//        //save to db
//        User createdUser= userRepository.save(user);
//
//        //set the id for user
//        UserDto userDto=new UserDto();
//        userDto.setId(createdUser.getId());
//
//        return userDto;
//    }
//
//    public Boolean hasUserWithEmail(String email){
//        //check email exist or not
//        return userRepository.findFirstByEmail(email).isPresent();
//    }
//
//    @PostConstruct //executed after the bean's properties have been set and the bean is fully initialized.
//    public void createAdmin(){
//        User admin=userRepository.findByRole(UserRole.ADMIN);
//
//        User adminDB=new User();
//
//        adminDB.setEmail("mainAdmin@gmail.com");
//        adminDB.setName("admin");
//        adminDB.setRole(UserRole.ADMIN);
//        adminDB.setPassword(new BCryptPasswordEncoder().encode("admin"));
//        userRepository.save(adminDB);
//    }
//}
import com.e_commerce.FoodCat.entity.User;
import com.e_commerce.FoodCat.enums.UserRole;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository; // Ensure you have this for user operations

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public UserDto createUser(SignupRequest signupRequest) {
        User user = new User();

        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setUsername(signupRequest.getUsername());
        // Use the injected PasswordEncoder
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRole(UserRole.CUSTOMER);

        // Save to DB
        User createdUser = userRepository.save(user);

        // Set the ID for user
        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());

        return userDto;
    }

    public Boolean hasUserWithEmail(String email) {
        // Check if email exists or not
        return userRepository.findFirstByEmail(email).isPresent();
    }

//    @PostConstruct // Executed after the bean's properties have been set
//    public void createAdmin() {
//        // Check if admin already exists
//        if (userRepository.findByRole(UserRole.ADMIN) == null) {
//            User adminDB = new User();
//            adminDB.setEmail("mainAdmin@gmail.com");
//            adminDB.setName("admin");
//            adminDB.setRole(UserRole.ADMIN);
//            adminDB.setPassword(passwordEncoder.encode("admin")); // Use the injected PasswordEncoder
//            userRepository.save(adminDB);
//        }
//    }

    @PostConstruct
    public void createAdmin() {
        List<User> admins = userRepository.findByRole(UserRole.ADMIN);
        if (admins.isEmpty()) {
            User adminDB = new User();
            adminDB.setEmail("mainAdmin@gmail.com");
            adminDB.setName("admin");
            adminDB.setRole(UserRole.ADMIN);
            adminDB.setPassword(passwordEncoder.encode("admin"));
            userRepository.save(adminDB);
        }
    }

    public User authenticate(String email, String rawPassword) {
        // Log the input values
        logger.info("Attempting to authenticate user with email: {}", email);
        logger.debug("Raw password provided: {}", rawPassword); // Be cautious with sensitive data
        Optional<User> optionalUser = userRepository.findFirstByEmail(email); // Fetch user by email

        // Check if the user is present
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            System.out.println("Raw Password: " + rawPassword);
            System.out.println("Stored Password: " + user.getPassword());// Get the User object
            // Check password match
            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                return user; // Authentication successful
            }
        }

        return null; // Authentication failed
    }
}
