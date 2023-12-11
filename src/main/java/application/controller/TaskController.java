package application.controller;

import api.controller.CrudControllerApi;
import api.controller.TaskControllerApi;
import api.dto.feedback.FeedBackRequest;
import api.dto.task.TaskRequest;
import api.dto.task.TaskResponse;
import api.dto.user.AuthorRequest;
import application.security.JwtTokenUtils;
import application.service.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Tag(name = "Task",description = "Tutorial task API")
@RestController
@RequestMapping("task")
public class TaskController extends CrudControllerApi<TaskRequest, TaskResponse> implements TaskControllerApi {
    private final TaskService service;
    private final HttpServletRequest request;
    private final JwtTokenUtils jwtTokenUtils;

    @Autowired
    public TaskController(TaskService service, HttpServletRequest request, JwtTokenUtils jwtTokenUtils) {
        super(service);
        this.service = service;
        this.request = request;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    public TaskResponse addExecutorForTask(TaskRequest taskRequest) {
        return service.addExecutorForTask(taskRequest);
    }

    @Override
    public List<TaskResponse> getAuthorTasks(AuthorRequest authorRequest) {
        return service.getTaskAuthor(authorRequest.getEmail(), authorRequest.getPage());
    }

    @Override
    public List<TaskResponse> getMyTasks(Integer page) {
        String token = request.getHeader("Authorization").substring(7);
        return service.getTaskAuthor(jwtTokenUtils.getUserName(token), page);
    }

    @Override
    public TaskResponse changeStatusTasks(TaskRequest taskRequest) {
        return service.changeStatus(taskRequest);
    }

    @Override
    public TaskResponse addFeedBackForTask(FeedBackRequest feedBackRequest) {
        return service.addFeedBackForTask(feedBackRequest);
    }

    @Override
    public TaskResponse deleteFeedBackForTask(FeedBackRequest feedBackRequest) {
        return service.deleteFeedBackForTask(feedBackRequest);
    }
}
