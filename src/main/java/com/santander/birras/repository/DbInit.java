package com.santander.birras.repository;

import com.santander.birras.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DbInit {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    private void postConstruct() {
        if (userRepository.findByUsername("santander-admin") == null) {
            User admin = new User();
            admin.setUsername("santander-user");
            admin.setPassword(bCryptPasswordEncoder.encode("tecnologia"));
            admin.setRole("ADMIN");
            userRepository.save(admin);
        }
    }
}
