package com.pFI.pFI_api.service.impl;

import com.pFI.pFI_api.entity.User;
import com.pFI.pFI_api.mapper.UserMapper;
import com.pFI.pFI_api.repository.UserRepo;
import com.pFI.pFI_api.entity.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    //@Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);
        if(user==null) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("user not found");
        }

        return new UserPrincipal(user);
    }
}
