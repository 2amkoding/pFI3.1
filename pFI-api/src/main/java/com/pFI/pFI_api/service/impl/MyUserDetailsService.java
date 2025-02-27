package com.pFI.pFI_api.service.impl;

import com.pFI.pFI_api.entity.User;
import com.pFI.pFI_api.mapper.UserMapper;
import com.pFI.pFI_api.repository.UserRepo;
import com.pFI.pFI_api.entity.UserPrincipal;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername (String username){
        return userRepo.findByUsername(username)
                .map(UserPrincipal::new)
                .orElseThrow(() -> {
                    log.error("User not found: {}", username);
                    return new UsernameNotFoundException("User not found");
                });
    }
}
