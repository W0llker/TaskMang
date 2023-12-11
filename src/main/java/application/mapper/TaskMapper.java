package application.mapper;

import api.dto.task.TaskRequest;
import api.dto.task.TaskResponse;
import api.mapper.SupperMapper;
import application.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper extends SupperMapper<Task, TaskRequest, TaskResponse> {
}
