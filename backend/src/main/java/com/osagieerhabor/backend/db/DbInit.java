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
            userRepository.deleteAll();//

            User dan = new User("user", passwordEncoder.encode("user@123"), "USER", "");
            User admin = new User("admin", passwordEncoder.encode("admin@123"), "ADMIN", "ACCESS_ALL", EnabledStatus.ENABLED);

            List<User> users = Arrays.asList(dan, admin);

            userRepository.saveAll(users);
        }
    }
}
