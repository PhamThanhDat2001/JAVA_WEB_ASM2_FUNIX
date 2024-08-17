package asm2.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "recruitment")
public class Recruitment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "created_at", length = 255)
    private String createdAt;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "experience", length = 255)
    private String experience;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "`rank`", length = 255)
    private String rank;

    @Column(name = "salary", length = 255)
    private String salary;

    @Column(name = "status")
    private Integer status;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "type", length = 255)
    private String type;

    @Column(name = "view")
    private Integer view;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "deadline", length = 255)
    private String deadline;

    @OneToMany(mappedBy = "recruitment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SaveJob> savedJobs;

    @OneToMany(mappedBy = "recruitment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ApplyPost> applyPosts;
    // Constructors, getters, and setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public List<SaveJob> getSavedJobs() {
        return savedJobs;
    }

    public void setSavedJobs(List<SaveJob> savedJobs) {
        this.savedJobs = savedJobs;
    }

    public List<ApplyPost> getApplyPosts() {
        return applyPosts;
    }

    public void setApplyPosts(List<ApplyPost> applyPosts) {
        this.applyPosts = applyPosts;
    }
}
