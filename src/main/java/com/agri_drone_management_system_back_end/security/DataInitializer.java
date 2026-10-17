package com.agri_drone_management_system_back_end.security;

import com.agri_drone_management_system_back_end.entity.AppUser;
import com.agri_drone_management_system_back_end.model.Role;
import com.agri_drone_management_system_back_end.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (!userRepository.existsByUsername("jaggu")) {
            AppUser admin = AppUser.builder()
                    .username("jaggu")
                    .password(passwordEncoder.encode("jaggu!@#$"))
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(admin);
        }

        if (!userRepository.existsByUsername("pilot")) {
            AppUser pilot = AppUser.builder()
                    .username("pilot")
                    .password(passwordEncoder.encode("jaggu!@#$"))
                    .role(Role.PILOT)
                    .build();
            userRepository.save(pilot);
        }
    }

}