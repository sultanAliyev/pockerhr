package kz.iitu.service.ui.controller;

import kz.iitu.service.configuration.jwt.AuthorizationStructure;
import kz.iitu.service.domain.document.service.IDocumentService;
import kz.iitu.service.ui.dto.docs.request.CreateDocumentRequest;
import kz.iitu.service.ui.dto.docs.request.PassDocumentRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static kz.iitu.service.configuration.jwt.AuthorizationStructure.AUTHORIZATION_HEADER;

@RestController
@RequestMapping("/api/document")
@AllArgsConstructor
@CrossOrigin("*")
public class DocumentController {

    private final AuthorizationStructure authorization;
    private final IDocumentService documentService;


    @PostMapping("/create")
ResponseEntity<?> createDocument(
        @RequestHeader(AUTHORIZATION_HEADER) String token,
        @RequestBody CreateDocumentRequest createDocumentRequest
    ) throws Exception {
        return authorization.checkHr(token, user -> documentService.createDocument(user, createDocumentRequest));
    }

    @GetMapping("/me")
    ResponseEntity<?> getDocumentsForMe(
        @RequestHeader(AUTHORIZATION_HEADER) String token
    ) throws Exception {
        return authorization.checkAny(token, documentService::findAllDocumentForMe);
    }

    @GetMapping("/my")
    ResponseEntity<?> getMyDocuments(
        @RequestHeader(AUTHORIZATION_HEADER) String token
    ) throws Exception {
        return authorization.checkAny(token, documentService::findAllMyDocuments);
    }

    @PostMapping("/pass")
    ResponseEntity<?> paddDocument(
        @RequestHeader(AUTHORIZATION_HEADER) String token,
        @RequestBody PassDocumentRequest passDocumentRequest
    ) throws Exception {
        return authorization.checkAny(token, user -> documentService.passDocument(user, passDocumentRequest));
    }
}
