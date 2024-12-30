package com.ntp.ntpshops.service.user;

import com.ntp.ntpshops.dto.UserDTO;
import com.ntp.ntpshops.model.User;
import com.ntp.ntpshops.request.CreateUserRequest;
import com.ntp.ntpshops.request.UserUpdateRequest;

public interface IUserService {
    User getUserById(Long userId);

    User createUser(CreateUserRequest request);

    User updateUser(UserUpdateRequest request, Long userId);

    void deleteUser(Long userId);

    UserDTO convertUserToDto(User user);

    User getAuthenticatedUser();
}
