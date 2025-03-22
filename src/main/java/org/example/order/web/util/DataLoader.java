package org.example.order.web.util;

import lombok.RequiredArgsConstructor;
import org.example.order.constants.RoleName;
import org.example.order.web.entity.Role;
import org.example.order.web.entity.User;
import org.example.order.web.repository.RoleRepository;
import org.example.order.web.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            Role superAdmin = new Role();
            superAdmin.setName(RoleName.SUPER_ADMIN);
            roleRepository.save(superAdmin);

            Role admin = new Role();
            admin.setName(RoleName.ADMIN);
            roleRepository.save(admin);

            Role waiter = new Role();
            waiter.setName(RoleName.WAITER);
            roleRepository.save(waiter);

            Role cashier = new Role();
            cashier.setName(RoleName.CASHIER);
            roleRepository.save(cashier);

            User user = new User();
            user.setName("admin");
            user.setRole(admin);
            user.setPhoneNumber("+998939288822");
            user.setPassword(passwordEncoder.encode("123"));
            userRepository.save(user);
        }
    }
}
