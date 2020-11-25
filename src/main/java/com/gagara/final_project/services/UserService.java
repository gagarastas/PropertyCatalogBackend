package com.gagara.final_project.services;

import com.gagara.final_project.entities.Role;
import com.gagara.final_project.entities.User;
import com.gagara.final_project.repositories.RoleRepository;
import com.gagara.final_project.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }
    @Transactional
    public void addUser(){
        String pass = passwordEncoder.encode("gagara");
        String s = passwordEncoder.encode("gaga:gaga");
        System.out.println(s);

        Role rl = roleRepository.getOne((long)1);
        Set<Role> roles = new HashSet<>();
        roles.add(rl);
        User newUser = new User("grgr","grgr",roles);
        userRepository.save(newUser);
        int i = 9;
    }
}
