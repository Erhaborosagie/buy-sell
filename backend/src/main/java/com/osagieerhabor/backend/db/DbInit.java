package com.osagieerhabor.backend.db;

import com.osagieerhabor.backend.enums.EnabledStatus;
import com.osagieerhabor.backend.model.User;
import com.osagieerhabor.backend.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findAll().size() == 0){
            userRepository.deleteAll();//No need for this

            User dan = new User("user@123.com", passwordEncoder.encode("user@123.com"), "USER", "");
            User admin = new User("admin@123.com", passwordEncoder.encode("admin@123.com"), "ADMIN", "ACCESS_ALL", EnabledStatus.ENABLED);

            List<User> users = Arrays.asList(dan, admin);

            userRepository.saveAll(users);
        }
    }
}
