package kz.iitu.service.domain.employee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long companyId;
    private Long userId;
    private String title;
    private Long createdDate;
    private Long updatedDate;
    private String salary;

    public Employee(Long companyId, Long userId, String title, Long createdDate, Long updatedDate, String salary) {
        this.companyId = companyId;
        this.userId = userId;
        this.title = title;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.salary = salary;
    }
}
