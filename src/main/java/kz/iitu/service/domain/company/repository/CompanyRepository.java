package kz.iitu.service.domain.company.repository;

import kz.iitu.service.domain.company.model.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

    Optional<Company> findByHrId(Long hrId);

}
