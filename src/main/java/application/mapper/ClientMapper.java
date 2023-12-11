package application.mapper;

import api.dto.user.UserRequest;
import api.dto.user.UserResponse;
import api.mapper.SupperMapper;
import application.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper extends SupperMapper<Client,UserRequest,UserResponse> {
}
