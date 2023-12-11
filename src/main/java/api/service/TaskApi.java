package api.service;

import api.dto.feedback.FeedBackRequest;
import api.dto.task.TaskRequest;
import api.dto.task.TaskResponse;

import java.util.List;

public interface TaskApi {
    List<TaskResponse> getTaskAuthor(String email, Integer page);

    TaskResponse addExecutorForTask(TaskRequest taskRequest);

    TaskResponse changeStatus(TaskRequest taskRequest);

    TaskResponse addFeedBackForTask(FeedBackRequest feedBackRequest);

    TaskResponse deleteFeedBackForTask(FeedBackRequest feedBackRequest);
}
