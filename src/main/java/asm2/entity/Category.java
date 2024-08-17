package asm2.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "number_choose")
    private Integer numberChoose;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Recruitment> recruitments;
    // Constructors, getters, and setters


    public Integer getNumberChoose() {
        return numberChoose;
    }

    public void setNumberChoose(Integer numberChoose) {
        this.numberChoose = numberChoose;
    }

    public List<Recruitment> getRecruitments() {
        return recruitments;
    }

    public void setRecruitments(List<Recruitment> recruitments) {
        this.recruitments = recruitments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
