package org.example.order.web.config.security;

import lombok.AllArgsConstructor;
import org.example.order.web.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
   private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        if (phoneNumber == null || phoneNumber.isBlank() || phoneNumber.isEmpty()) {
            throw new UsernameNotFoundException("Invalid phone number");
        }
        return userRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }
}
