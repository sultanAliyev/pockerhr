package kz.iitu.service.domain.document.service;

import kz.iitu.service.domain.user.model.User;
import kz.iitu.service.ui.dto.docs.request.CreateDocumentRequest;
import kz.iitu.service.ui.dto.docs.request.PassDocumentRequest;
import org.springframework.http.ResponseEntity;

public interface IDocumentService {

    ResponseEntity<?> createDocument(User user, CreateDocumentRequest createDocumentRequest);

    ResponseEntity<?> findAllDocumentForMe(User user);

    ResponseEntity<?> findAllMyDocuments(User user);

    ResponseEntity<?> passDocument(User user, PassDocumentRequest passDocumentRequest);

}
