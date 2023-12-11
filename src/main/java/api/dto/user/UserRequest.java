package api.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String surName;
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotNull
    private Role role;
}
