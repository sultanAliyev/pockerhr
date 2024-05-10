package kz.iitu.service.domain.route.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "route")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long documentId;
    private Long userId;
    private Long signatureDate;

    public Route(Long documentId, Long userId, Long signatureDate) {
        this.documentId = documentId;
        this.userId = userId;
        this.signatureDate = signatureDate;
    }

}
