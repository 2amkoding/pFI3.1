package com.pFI.pFI_api.service.impl;


import com.pFI.pFI_api.dto.UserDTO;
import com.pFI.pFI_api.entity.User;
import com.pFI.pFI_api.exception.ResourceNotFoundException;
import com.pFI.pFI_api.mapper.UserMapper;
import com.pFI.pFI_api.repository.UserRepo;
import com.pFI.pFI_api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    @Override
    public UserDTO createUser(UserDTO userDTO) {

        User user = UserMapper.mapToUser(userDTO);
        User savedUser = userRepo.save(user);


        return UserMapper.mapToUserDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with given id: " + userId));

        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map((user) -> UserMapper.mapToUserDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO updatedUser) {

        User user = userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User does not exist with id: " + userId)
        );

        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());

        User updatedUserObj = userRepo.save(user);

        return UserMapper.mapToUserDTO(updatedUserObj);
    }

    @Override
    public void deleteUser(Long userId) {

        User user = userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User does not exist with id: " + userId)
        );

        userRepo.deleteById(userId);
    }
}