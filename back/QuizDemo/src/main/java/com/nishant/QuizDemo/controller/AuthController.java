package com.nishant.QuizDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nishant.QuizDemo.exception.AppException;
import com.nishant.QuizDemo.model.Role;
import com.nishant.QuizDemo.model.RoleName;
import com.nishant.QuizDemo.model.User;
import com.nishant.QuizDemo.payload.ApiResponse;
import com.nishant.QuizDemo.payload.JwtAuthenticationResponse;
import com.nishant.QuizDemo.payload.LoginRequest;
import com.nishant.QuizDemo.payload.SignUpRequest;
import com.nishant.QuizDemo.repository.RoleRepository;
import com.nishant.QuizDemo.repository.UserRepository;
import com.nishant.QuizDemo.security.JwtTokenProvider;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    	System.out.println("request body for login "+loginRequest);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail().trim(),
                        loginRequest.getPassword().trim()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        User user;
        Role role=null;
        String emailId = "";
        Optional data = userRepository.findByUsernameOrEmail(loginRequest.getUsernameOrEmail(), loginRequest.getUsernameOrEmail());
        if(data.isPresent()) {
        	user = (User) data.get(); 
        	role = (Role) user.getRoles().toArray()[0];
        	emailId = (String)user.getEmail();
        	System.out.println("Role role=> "+role);
        	System.out.println(user);
        }
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt,role,emailId));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
    	System.out.println("request body for signUpRequest "+signUpRequest);
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.ALREADY_REPORTED);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.ALREADY_REPORTED);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)  
                .orElseThrow(() -> new AppException("User Role not set."));  //change ROLE_USER to ROLE_ADMIN to register user or admin 

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}