package kz.iitu.service.domain.document.repository;

import kz.iitu.service.domain.document.model.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long> {

    List<Document> findAllByCreatorId(Long creatorId);

}
