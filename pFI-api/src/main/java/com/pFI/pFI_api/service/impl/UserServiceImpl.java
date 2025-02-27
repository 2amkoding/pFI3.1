package com.pFI.pFI_api.service.impl;


import com.pFI.pFI_api.dto.UserDTO;
import com.pFI.pFI_api.dto.UserRegistrationDTO;
import com.pFI.pFI_api.entity.User;
import com.pFI.pFI_api.exception.DuplicateResourceException;
import com.pFI.pFI_api.exception.ResourceNotFoundException;
import com.pFI.pFI_api.mapper.UserMapper;
import com.pFI.pFI_api.repository.UserRepo;
import com.pFI.pFI_api.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {

//        User user = UserMapper.mapToUser(userDTO);
//        User savedUser = userRepo.save(user);
//
//
//        return UserMapper.mapToUserDTO(savedUser);
        //WIP
        if(userRepo.existsByUsername(userDTO.getUsername())){
            throw new DuplicateResourceException("username already exists");
        }
        if(userRepo.existsByEmail(userDTO.getEmail())){
            throw new DuplicateResourceException("email already exists");
        }

        User user = UserMapper.mapToUser(userDTO);
        User savedUser = userRepo.save(user);
        log.info("user created sucessfully w username: {}", savedUser.getUsername());

        return UserMapper.mapToUserDTO(savedUser);
    }

    @Override
    @Transactional
    public UserDTO registerUser(UserRegistrationDTO registrationDTO) {
        validateUniqueConstraints(registrationDTO);

        User user = UserMapper.mapToRegistration(registrationDTO);
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

        User savedUser = userRepo.save(user);
        log.info("user registered successfully w username: {}", savedUser.getUsername());

        return UserMapper.mapToUserDTO(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(Long userId) {
        return userRepo.findById(userId)
                .map(UserMapper::mapToUserDTO)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with given id: " + userId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map(UserMapper::mapToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDTO updateUser(Long userId, UserDTO updatedUser) {

        User user = userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User does not exist with id: " + userId)
        );

        validateUpdateConstraints(user, updatedUser);

        updateUserFields(user, updatedUser);

        User updatedUserObj = userRepo.save(user);
        log.info("User updated successfully w id: {}", userId);

        return UserMapper.mapToUserDTO(updatedUserObj);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        if(!userRepo.existsById(userId)) {
            throw new ResourceNotFoundException("User does not exist w id: " + userId);
        }

        userRepo.deleteById(userId);
        log.info("User deleted sucessfully w id: {}", userId);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserByUsername(String username) {
        return userRepo.findByUsername(username)
                    .map(UserMapper::mapToUserDTO)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found w username: " + username));

    }

    private void validateUniqueConstraints(UserRegistrationDTO dto) {
        if(userRepo.existsByUsername(dto.getUsername())) {
            throw new DuplicateResourceException("Username already exists");
        }
        if(userRepo.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }
    }

    private void validateUpdateConstraints(User existingUser, UserDTO updatedUser) {
        if(!existingUser.getUsername().equals(updatedUser.getUsername())
                && userRepo.existsByUsername(updatedUser.getUsername())) {
            throw new DuplicateResourceException("Username already exists");
        }
        if(!existingUser.getEmail().equals(updatedUser.getEmail())
                && userRepo.existsByEmail(updatedUser.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }
    }

    private void updateUserFields(User user, UserDTO updatedUser){
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setUsername(updatedUser.getUsername());
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

}