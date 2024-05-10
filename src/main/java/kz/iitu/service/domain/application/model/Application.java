package kz.iitu.service.domain.application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "application")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long jobId;
    private Long userId;
    private Long createdDate;
    private Long updatedDate;
    private ApplicationStatus status;

    public Application(Long jobId, Long userId, Long createdDate, Long updatedDate, ApplicationStatus status) {
        this.jobId = jobId;
        this.userId = userId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.status = status;
    }
}
