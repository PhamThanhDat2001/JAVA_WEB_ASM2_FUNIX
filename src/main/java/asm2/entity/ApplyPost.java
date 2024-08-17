package asm2.entity;

import javax.persistence.*;

@Entity
@Table(name = "applypost")
public class ApplyPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "created_at", length = 255)
    private String createdAt;

    @ManyToOne
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "name_cv", length = 255)
    private String nameCv;

    @Column(name = "status")
    private Integer status;

    @Column(name = "text", length = 255)
    private String text;

    @Transient
    private String applicationType;
    // Constructors, getters, and setters


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNameCv() {
        return nameCv;
    }

    public void setNameCv(String nameCv) {
        this.nameCv = nameCv;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recruitment getRecruitment() {
        return recruitment;
    }

    public void setRecruitment(Recruitment recruitment) {
        this.recruitment = recruitment;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }
}
