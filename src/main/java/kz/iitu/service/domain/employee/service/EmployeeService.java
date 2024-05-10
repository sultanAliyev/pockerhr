package kz.iitu.service.domain.employee.service;

import kz.iitu.service.domain.company.model.Company;
import kz.iitu.service.domain.employee.model.Employee;
import kz.iitu.service.domain.employee.repository.EmployeeRepository;
import kz.iitu.service.domain.user.model.User;
import kz.iitu.service.ui.dto.employee.mapper.EmployeeMapper;
import kz.iitu.service.ui.dto.employee.request.EmployeeRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    private final EmployeeMapper mapper;

    public ResponseEntity<?> getUserPosition(User user) {
        Optional<Employee> employeeOptional = repository.findByUserId(user.getId());
        if (employeeOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Position not exists");
        } else {
            return ResponseEntity.ok(employeeOptional.get());
        }
    }

    public ResponseEntity<?> findAll(Company company) {
        return ResponseEntity.ok(mapper.parse(repository.findAllByCompanyId(company.getId())));
    }

    public ResponseEntity<?> delete(Company company, Long id) {
        Optional<Employee> employeeOptional = repository.findById(id);
        if (employeeOptional.isEmpty()) {
            return ResponseEntity.ok("Deleted");
        }
        Employee employee = employeeOptional.get();
        if (!Objects.equals(employee.getCompanyId(), company.getId())) {
            return ResponseEntity.ok("Deleted");
        }
        repository.delete(employee);
        return ResponseEntity.ok("Deleted");
    }

    public ResponseEntity<?> update(Company company, EmployeeRequest employeeRequest) {
        Optional<Employee> employeeOptional = repository.findById(employeeRequest.getEmployeeId());
        if (employeeOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Not found");
        }
        Employee employee = employeeOptional.get();
        if (!Objects.equals(employee.getCompanyId(), company.getId())) {
            return ResponseEntity.badRequest().body("Not found");
        }
        employee.setTitle(employeeRequest.getTitle());
        employee.setSalary(employeeRequest.getSalary());
        employee.setUpdatedDate(System.currentTimeMillis());
        repository.save(employee);
        return ResponseEntity.ok(mapper.parse(employee));
    }

}
