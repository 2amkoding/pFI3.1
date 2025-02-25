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
        return new User(
                userDTO.getId(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmail(),
                userDTO.getUsername()
        );
    }

    public static User mapToRegistration(UserRegistrationDTO dto) {
        return new User(
                null,
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getUsername(),
                dto.getPassword()
        );
    }
}