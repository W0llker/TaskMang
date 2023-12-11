package application.mapper;

import api.dto.feedback.FeedBackRequest;
import api.dto.feedback.FeedBackResponse;
import api.mapper.SupperMapper;
import application.entity.FeedBack;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeedBackMapper extends SupperMapper<FeedBack, FeedBackRequest, FeedBackResponse> {
}
