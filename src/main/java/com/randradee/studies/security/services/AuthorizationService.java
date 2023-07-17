package com.randradee.studies.security.services;

import com.randradee.studies.security.domain.user.RegisterDTO;
import com.randradee.studies.security.domain.user.User;
import com.randradee.studies.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }

    public void createUser(RegisterDTO userToRegister) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(userToRegister.password());
        User newUser = new User(userToRegister.login(), encryptedPassword, userToRegister.role());
        userRepository.save(newUser);
    }


}
