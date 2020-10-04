package com.santander.birras.repository;

import com.santander.birras.model.User;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void createUserTest() {
        User user = new User();
        user.setId(1L);
        user.setUsername("santander-user");
        user.setPassword(bCryptPasswordEncoder.encode("tecnologia"));
        user.setRole("ADMIN");

        User userReturn = userRepository.save(user);
        assertTrue(userReturn.getPassword().equalsIgnoreCase(user.getPassword()));
        userRepository.delete(user);
    }
}
