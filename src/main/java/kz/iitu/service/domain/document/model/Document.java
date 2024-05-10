package kz.iitu.service.domain.document.model;


import javax.persistence.*;

@Entity
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long creatorId;
    private Long companyId;
    private Long createdDate;
    private Integer stepSize;
    private Integer step;
    private String content;
    private String type;

    public Document() {
    }

    public Document(Long creatorId, Long companyId, Long createdDate, Integer stepSize, Integer step, String content, String type) {
        this.creatorId = creatorId;
        this.companyId = companyId;
        this.createdDate = createdDate;
        this.stepSize = stepSize;
        this.step = step;
        this.content = content;
        this.type = type;
    }

    public Document(Long id, Long creatorId, Long companyId, Long createdDate, Integer stepSize, Integer step, String content, String type) {
        this.id = id;
        this.creatorId = creatorId;
        this.companyId = companyId;
        this.createdDate = createdDate;
        this.stepSize = stepSize;
        this.step = step;
        this.content = content;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getStepSize() {
        return stepSize;
    }

    public void setStepSize(Integer stepSize) {
        this.stepSize = stepSize;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
