package com.pFI.pFI_api.dto;

import lombok.*;
import org.hibernate.annotations.processing.Pattern;
import org.springframework.http.converter.json.GsonBuilderUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {
    @NotBlank(message = "first name is required")
    private String firstName;

    @NotBlank(message = "last name is required")
    private String lastName;

    @NotBlank(message = "email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "username is required")
    @Size(min = 4, max = 50)
    private String username;

    @NotBlank(message = "password is required")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
                message = "password must be at least 8 characters and contain both letters and numbers"  )
    private String password;
}
