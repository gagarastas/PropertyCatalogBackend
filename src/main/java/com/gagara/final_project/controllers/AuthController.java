package com.gagara.final_project.controllers;



import com.gagara.final_project.entities.Role;
import com.gagara.final_project.entities.User;
import com.gagara.final_project.payload.ApiResponse;
import com.gagara.final_project.payload.JwtAuthenticationResponse;
import com.gagara.final_project.payload.LoginRequest;
import com.gagara.final_project.payload.SignUpRequest;
import com.gagara.final_project.repositories.RoleRepository;
import com.gagara.final_project.repositories.UserRepository;
import com.gagara.final_project.security.JwtTokenProvider;
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

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
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




    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        Collection<?> roles = authentication.getAuthorities();
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, roles));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByLogin(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        // in this case each new user will have only 'user' role
        // if you want to add admin, you can insert admin info into users table in V3 migration file and set up 'admin' role
        String password = passwordEncoder.encode(signUpRequest.getPassword());
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.getOne((long)1);
        roles.add(role);
        User user = new User(signUpRequest.getUsername(), password, roles);
        userRepository.save(user);

        return ResponseEntity.ok().body(new ApiResponse(true, "User registered successfully"));
    }

}

