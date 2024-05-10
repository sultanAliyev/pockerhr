package kz.iitu.service.ui.dto.employee.mapper;

import kz.iitu.service.domain.employee.model.Employee;
import kz.iitu.service.domain.user.repository.UserRepository;
import kz.iitu.service.ui.dto.employee.response.EmployeeResponse;
import kz.iitu.service.ui.dto.user.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class EmployeeMapper {

    private final UserRepository repository;

    private final UserMapper mapper;

    public EmployeeResponse parse(Employee employee) {
        return new EmployeeResponse(
            employee.getId(),
            employee.getTitle(),
            employee.getSalary(),
            employee.getCreatedDate(),
            employee.getUpdatedDate(),
            mapper.parse(repository.findById(employee.getUserId()).get())
        );
    }

    public List<EmployeeResponse> parse(List<Employee> employees) {
        List<EmployeeResponse> responses = new ArrayList<>();
        for (Employee employee : employees) {
            responses.add(parse(employee));
        }
        return responses;
    }

}
