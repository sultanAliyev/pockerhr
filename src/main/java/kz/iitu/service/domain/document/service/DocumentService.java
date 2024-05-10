package kz.iitu.service.domain.document.service;

import kz.iitu.service.domain.user.model.User;
import kz.iitu.service.ui.dto.docs.request.CreateDocumentRequest;
import kz.iitu.service.ui.dto.docs.request.PassDocumentRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class DocumentService implements IDocumentService {


    @Override
    public ResponseEntity<?> createDocument(User user, CreateDocumentRequest createDocumentRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> findAllDocumentForMe(User user) {
        return null;
    }

    @Override
    public ResponseEntity<?> findAllMyDocuments(User user) {
        return null;
    }

    @Override
    public ResponseEntity<?> passDocument(User user, PassDocumentRequest passDocumentRequest) {
        return null;
    }
}
