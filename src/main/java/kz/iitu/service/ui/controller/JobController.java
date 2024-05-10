package kz.iitu.service.ui.controller;

import kz.iitu.service.configuration.jwt.AuthorizationStructure;
import kz.iitu.service.domain.job.service.JobService;
import kz.iitu.service.ui.dto.job.request.JobRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static kz.iitu.service.configuration.jwt.AuthorizationStructure.AUTHORIZATION_HEADER;


@RestController
@RequestMapping("/api/job")
@AllArgsConstructor
@CrossOrigin("*")
public class JobController {

    private final AuthorizationStructure authorization;

    private final JobService service;

    @PostMapping
    public ResponseEntity<?> create(
        @RequestHeader(AUTHORIZATION_HEADER) String token,
        @RequestBody JobRequest jobRequest
    ) throws Exception {
       return authorization.checkHr(token, user -> service.create(user, jobRequest));
    }

    @PutMapping
    public ResponseEntity<?> update(
        @RequestHeader(AUTHORIZATION_HEADER) String token,
        @RequestBody JobRequest jobRequest
    ) throws Exception {
        return authorization.checkHr(token, user -> service.update(user, jobRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
        @RequestHeader(AUTHORIZATION_HEADER) String token,
        @PathVariable Long id
    ) throws Exception {
        return authorization.checkHr(token, user -> service.delete(user, id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(
        @RequestHeader(AUTHORIZATION_HEADER) String token
    ) throws Exception {
        return authorization.checkAny(token, user -> service.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(
        @RequestHeader(AUTHORIZATION_HEADER) String token,
        @RequestParam String word
    ) throws Exception {
        return authorization.checkAny(token, user -> service.search(word));
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<?> byCompany(
        @RequestHeader(AUTHORIZATION_HEADER) String token,
        @PathVariable Long companyId
    ) throws Exception {
        return authorization.checkAny(token, user -> service.byCompany(companyId));
    }
}
