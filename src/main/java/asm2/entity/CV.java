package asm2.entity;

import javax.persistence.*;

@Entity
@Table(name = "cv")
public class CV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "file_name", length = 255)
    private String fileName;

//    @OneToOne(mappedBy = "cv")
//    private User user;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors, getters, and setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

