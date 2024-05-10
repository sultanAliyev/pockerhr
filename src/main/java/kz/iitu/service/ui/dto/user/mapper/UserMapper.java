package kz.iitu.service.ui.dto.user.mapper;

import kz.iitu.service.domain.user.model.User;
import kz.iitu.service.ui.dto.user.response.ProfileResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public ProfileResponse parse(User user) {
        return new ProfileResponse(user.getId(), user.getUsername(), user.getEmail(), user.getPhone(), user.getPassword(), user.getAvatarUrl());
    }

}
