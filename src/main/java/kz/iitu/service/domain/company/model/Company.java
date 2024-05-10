package kz.iitu.service.domain.company.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "company")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long hrId;
    private String name;
    private String avatarUrl;
    private String description;
    private String address;
    private String phone;

    public Company(Long hrId, String name, String avatarUrl, String description, String address, String phone) {
        this.hrId = hrId;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.description = description;
        this.address = address;
        this.phone = phone;
    }
}
