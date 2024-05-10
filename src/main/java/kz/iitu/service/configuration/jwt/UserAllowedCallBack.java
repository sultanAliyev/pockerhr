package kz.iitu.service.configuration.jwt;

import kz.iitu.service.domain.user.model.User;
import org.springframework.http.ResponseEntity;

public interface UserAllowedCallBack {

    ResponseEntity<?> allowed(User user);

}