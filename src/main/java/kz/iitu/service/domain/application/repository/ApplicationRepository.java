package kz.iitu.service.domain.application.repository;

import kz.iitu.service.domain.application.model.Application;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Long> {

    List<Application> findAllByUserId(Long userId);

    List<Application> findAllByJobId(Long jobId);

    Optional<Application> findByJobIdAndUserId(Long jobId, Long userId);

}
