package com.pFI.pFI_api.mapper;


import com.pFI.pFI_api.dto.UserDTO;
import com.pFI.pFI_api.dto.UserRegistrationDTO;
import com.pFI.pFI_api.entity.User;

public class UserMapper {

    public static UserDTO mapToUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUsername()
        );
    }

    public static User mapToUser(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .username(userDTO.getUsername())
                .build();
    }

    public static User mapToRegistration(UserRegistrationDTO dto) {
        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }
}