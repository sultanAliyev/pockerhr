package kz.iitu.service.domain.route.repository;

import kz.iitu.service.domain.route.model.Route;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends CrudRepository<Route, Long> {

    List<Route> findAllByDocumentId(Long documentId);

    List<Route> findAllByUserId(Long userId);

}
