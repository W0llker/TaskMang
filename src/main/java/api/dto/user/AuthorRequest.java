package api.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthorRequest {
    @Email
    private String email;
    @NotNull
    private Integer page;
}
