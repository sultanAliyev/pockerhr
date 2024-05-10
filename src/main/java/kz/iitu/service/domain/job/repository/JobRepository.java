package kz.iitu.service.domain.job.repository;

import kz.iitu.service.domain.job.model.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {

    List<Job> findAllByCompanyId(Long companyId);

    List<Job> searchByNameContainingIgnoreCase(String name);

}
