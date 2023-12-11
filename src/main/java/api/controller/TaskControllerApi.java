package api.controller;

import api.dto.feedback.FeedBackRequest;
import api.dto.task.TaskRequest;
import api.dto.task.TaskResponse;
import api.dto.user.AuthorRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface TaskControllerApi {
    @GetMapping("/author")
    List<TaskResponse> getAuthorTasks(@RequestBody AuthorRequest authorRequest);

    @GetMapping("/my")
    List<TaskResponse> getMyTasks(@RequestParam Integer page);

    @PostMapping("/add/feedback")
    TaskResponse addFeedBackForTask(@RequestBody @Valid FeedBackRequest feedBackRequest);

    @DeleteMapping("/delete/feedback")
    TaskResponse deleteFeedBackForTask(@RequestBody FeedBackRequest feedBackRequest);

    @PostMapping("/add/executor")
    TaskResponse addExecutorForTask(@RequestBody TaskRequest taskRequest);

    @PutMapping("/executor")
    TaskResponse changeStatusTasks(@RequestBody TaskRequest taskRequest);

}
