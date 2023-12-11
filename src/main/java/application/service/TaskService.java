package application.service;

import api.dto.feedback.FeedBackRequest;
import api.dto.task.TaskRequest;
import api.dto.task.TaskResponse;
import api.dto.user.Role;
import api.service.CrudService;
import api.service.TaskApi;
import application.entity.Task;
import application.entity.Client;
import application.exception.TaskOperationException;
import application.exception.UserOperationException;
import application.mapper.FeedBackMapper;
import application.mapper.TaskMapper;
import application.repository.TaskRepository;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService extends CrudService<Task, TaskRequest, TaskResponse> implements TaskApi {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final FeedBackMapper feedBackMapper;

    @Autowired
    public TaskService(TaskRepository repository, TaskMapper mapper, UserRepository userRepository, FeedBackMapper feedBackMapper) {
        super(repository, mapper);
        this.taskRepository = repository;
        this.taskMapper = mapper;
        this.userRepository = userRepository;
        this.feedBackMapper = feedBackMapper;
    }

    @Override
    public TaskResponse addExecutorForTask(TaskRequest taskRequest) {
        if (taskRequest.getExecutor() == null || taskRequest.getId() == null)
            throw new TaskOperationException("Данные введены неверно");

        Task task = taskRepository.findById(taskRequest.getId())
                .orElseThrow(() -> new TaskOperationException("Исполнитель не найден"));

        Client client = userRepository.findById(taskRequest.getExecutor().getId())
                .orElseThrow(() -> new UserOperationException("Исполнитель не найден"));

        if (client.getRole().equals(Role.EXECUTOR) && task.getExecutor() == null) {
            task.setExecutor(client);
            taskRepository.save(task);
            return taskMapper.createResponse(taskRepository.save(task));
        }
        throw new TaskOperationException("Невозможно добавить исполнителя");
    }

    @Override
    public List<TaskResponse> getTaskAuthor(String email, Integer page) {
        Client client = userRepository.findByEmail(email);
        if (client == null) {
            throw new TaskOperationException("Данного пользователя нету");
        }

        if (client.getRole().equals(Role.AUTHOR))
            return taskRepository.findByAuthor(client, PageRequest.of(page, 10))
                .stream()
                .map(taskMapper::createResponse)
                .toList();
        else
            return taskRepository.findByExecutor(client, PageRequest.of(page, 10))
                    .stream()
                    .map(taskMapper::createResponse)
                    .toList();
    }

    @Override
    public TaskResponse changeStatus(TaskRequest taskRequest) {
        if (taskRequest.getStatus() != null) {
            Task task = taskRepository.findById(taskRequest.getId())
                    .orElseThrow(() -> new TaskOperationException("Исполнитель не найден"));
            task.setStatus(taskRequest.getStatus());
            return taskMapper.createResponse(task);
        }
        return null;
    }

    @Override
    public TaskResponse addFeedBackForTask(FeedBackRequest feedBackRequest) {
        Task task = taskRepository
                .findById(feedBackRequest.getIdTask())
                .orElseThrow(() -> new TaskOperationException("Нету такой задачи"));

        task.getFeedBack()
                .add(feedBackMapper.createEntity(feedBackRequest));

        taskRepository.save(task);
        return taskMapper.createResponse(task);

    }

    @Override
    public TaskResponse deleteFeedBackForTask(FeedBackRequest feedBackRequest) {
        Task task = taskRepository
                .findById(feedBackRequest.getIdTask())
                .orElseThrow(() -> new TaskOperationException("Нету такой задачи"));

        task.getFeedBack()
                .remove(feedBackMapper.createEntity(feedBackRequest));

        taskRepository.save(task);
        return taskMapper.createResponse(task);
    }
}
