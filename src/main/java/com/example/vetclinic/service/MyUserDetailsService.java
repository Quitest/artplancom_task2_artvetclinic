package com.example.vetclinic.service;

import com.example.vetclinic.entity.User;
import com.example.vetclinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("No user found with username: " + email);
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialNonExpired = true;
        boolean accountNonBlocked = true;

        return new  org.springframework.security.core.userdetails.User(
                user.getEmail(),user.getPassword().toLowerCase(), enabled,accountNonExpired,credentialNonExpired,
                accountNonBlocked, getAuthorities(user.getRoles())
        );
    }

    private static List<GrantedAuthority> getAuthorities(List<String> roles){
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

}
