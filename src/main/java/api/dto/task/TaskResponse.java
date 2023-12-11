package api.dto.task;

import api.dto.feedback.FeedBackResponse;
import api.dto.user.UserResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private UserResponse author;
    private UserResponse executor;
    private List<FeedBackResponse> feedBack;
}
