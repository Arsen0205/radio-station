package com.example.radio_station.service;

import com.example.radio_station.models.User;
import com.example.radio_station.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {
        // Ищем пользователя по логину
        User user = userRepository.findByUserLogin(userLogin)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + userLogin));

        // Преобразуем вашу модель User в UserDetails
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserLogin())
                .password(user.getPassword())
                .authorities(user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.name()))
                        .toList())
                .build();
    }
}
