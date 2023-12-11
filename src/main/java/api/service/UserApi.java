package api.service;

import api.dto.user.UserResponse;

public interface UserApi {
    UserResponse findUserEmail(String email);
}
