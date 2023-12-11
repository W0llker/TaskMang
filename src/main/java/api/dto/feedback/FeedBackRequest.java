package api.dto.feedback;

import api.dto.user.UserRequest;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeedBackRequest {
    private Long idTask;
    @NotBlank
    private String message;
    @Min(1)
    @Max(5)
    private Integer stars;
    @NotNull
    private UserRequest user;
}
