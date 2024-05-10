package kz.iitu.service.domain.job.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "job")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long companyId;
    private String name;
    private String description;
    private String salary;
    private String city;
    private String type;

    public Job(Long companyId, String name, String description, String salary, String city, String type) {
        this.companyId = companyId;
        this.name = name;
        this.description = description;
        this.salary = salary;
        this.city = city;
        this.type = type;
    }
}
