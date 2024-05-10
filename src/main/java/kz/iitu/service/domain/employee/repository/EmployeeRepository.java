package kz.iitu.service.domain.employee.repository;

import kz.iitu.service.domain.employee.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findAllByCompanyId(Long companyId);

    Optional<Employee> findByUserId(Long userId);

}
