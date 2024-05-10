package kz.iitu.service.ui.controller;

import kz.iitu.service.configuration.jwt.AuthorizationStructure;
import kz.iitu.service.domain.application.service.ApplicationService;
import kz.iitu.service.ui.dto.application.request.AcceptRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static kz.iitu.service.configuration.jwt.AuthorizationStructure.AUTHORIZATION_HEADER;

@RestController
@RequestMapping("/api/application")
@AllArgsConstructor
@CrossOrigin("*")
public class ApplicationController {

    private final ApplicationService service;

    private final AuthorizationStructure authorization;


    @PostMapping("/request/{jobId}")
    public ResponseEntity<?> request(
        @RequestHeader(AUTHORIZATION_HEADER) String token,
        @PathVariable Long jobId
    ) throws Exception {
        return authorization.checkUser(token, user -> service.request(user, jobId));
    }

    @GetMapping("/user")
    public ResponseEntity<?> findAllForUser(
        @RequestHeader(AUTHORIZATION_HEADER) String token
    ) throws Exception {
        return authorization.checkUser(token, service::findAllForUser);
    }

    @GetMapping("/hr")
    public ResponseEntity<?> findAllForHr(
        @RequestHeader(AUTHORIZATION_HEADER) String token
    ) throws Exception {
        return authorization.checkHr(token, service::findAllForHr);
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<?> findAllForJob(
        @RequestHeader(AUTHORIZATION_HEADER) String token,
        @PathVariable Long id
    ) throws Exception {
        return authorization.checkHr(token, user -> service.findAllForJob(user, id));
    }

    @PostMapping("/{applicationId}/accept")
    public ResponseEntity<?> accept(
        @RequestHeader(AUTHORIZATION_HEADER) String token,
        @PathVariable Long applicationId
    ) throws Exception {
        return authorization.checkHr(token, user -> service.accept(user, applicationId));
    }

    @PostMapping("/{applicationId}/reject")
    public ResponseEntity<?> reject(
        @RequestHeader(AUTHORIZATION_HEADER) String token,
        @PathVariable Long applicationId
    ) throws Exception {
        return authorization.checkHr(token, user -> service.reject(user, applicationId));
    }

    @PostMapping("/{applicationId}/finish")
    public ResponseEntity<?> finish(
        @RequestHeader(AUTHORIZATION_HEADER) String token,
        @PathVariable Long applicationId,
        @RequestBody AcceptRequest acceptRequest
    ) throws Exception {
        return authorization.checkHr(token, user -> service.finish(user, applicationId, acceptRequest));
    }
}
