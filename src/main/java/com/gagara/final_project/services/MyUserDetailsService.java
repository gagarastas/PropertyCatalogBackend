package com.gagara.final_project.services;

import com.gagara.final_project.entities.User;
import com.gagara.final_project.security.UserPrincipal;
import com.gagara.final_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = repository.findByLogin(login);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

//        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));
        return UserPrincipal.create(user);
        //return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getHashPassword(), authorities);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = repository.findById(id).orElseThrow(
            () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return UserPrincipal.create(user);
    }
}
