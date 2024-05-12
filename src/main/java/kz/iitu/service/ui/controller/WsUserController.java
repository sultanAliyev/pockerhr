package kz.iitu.service.ui.controller;

import kz.iitu.service.domain.user.model.User;
import kz.iitu.service.domain.user.model.UserMongo;
import kz.iitu.service.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WsUserController {

    private final UserService userService;

    @MessageMapping("/user.addUser")
    @SendTo("/user/public")
    public UserMongo addUser(@Payload UserMongo user) {
        userService.saveUser(user);
        return user;
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/public")
    public UserMongo disconnectUser(@Payload UserMongo user) {
        userService.disconnect(user);
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserMongo>> findConnectedUsers() {
        return ResponseEntity.ok(userService.findConnectedUsers());
    }
}
