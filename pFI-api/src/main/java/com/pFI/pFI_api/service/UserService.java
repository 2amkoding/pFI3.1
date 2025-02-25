package com.pFI.pFI_api.service;

import com.pFI.pFI_api.dto.UserDTO;
import com.pFI.pFI_api.dto.UserRegistrationDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);

    UserDTO getUserById(Long userId);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(Long userId, UserDTO updatedUser);

    void deleteUser(Long userId);

    UserDTO registerUser(UserRegistrationDTO userRegistrationDTO);

    UserDTO getUserByUsername(String username);

    boolean existsByUsername (String username);

    boolean existsByEmail (String email);

}