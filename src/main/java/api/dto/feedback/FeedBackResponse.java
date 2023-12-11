package api.dto.feedback;

import api.dto.user.UserResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeedBackResponse {
    private String message;
    private Integer stars;
    private UserResponse user;
}
