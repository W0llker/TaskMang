package api.dto.task;

import api.dto.user.UserRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class TaskRequest {
    private Long id;
    @NotBlank
    @Length(max = 100)
    private String title;
    @NotBlank
    private String description;
    private TaskStatus status = TaskStatus.WAIT;
    @NotNull
    private TaskPriority priority;
    @NotNull
    private UserRequest author;
    private UserRequest executor;
}
