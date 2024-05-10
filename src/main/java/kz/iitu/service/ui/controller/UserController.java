package kz.iitu.service.ui.controller;

import kz.iitu.service.configuration.jwt.AuthorizationStructure;
import kz.iitu.service.domain.user.service.UserService;
import kz.iitu.service.ui.dto.user.request.UserDataRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static kz.iitu.service.configuration.jwt.AuthorizationStructure.AUTHORIZATION_HEADER;


@RestController
@RequestMapping("/auth/profile")
@AllArgsConstructor
public class UserController {

    private final UserService service;
    private final AuthorizationStructure authorization;

    @GetMapping
    public ResponseEntity<?> getProfile(@RequestHeader(AUTHORIZATION_HEADER) String token) {
        return authorization.checkAny(token, service::getProfile);
    }

    @PostMapping
    public ResponseEntity<?> changeProfile(@RequestHeader(AUTHORIZATION_HEADER) String token, @RequestBody UserDataRequest userDataRequest) {
        return authorization.checkAny(token, (user) -> service.changeProfile(user, userDataRequest));
    }
}
