    package asm2.entity;

    import javax.persistence.*;
    import java.util.List;

    @Entity
    @Table(name = "user")
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Integer id;

        @Column(name = "address", length = 255)
        private String address;

        @Column(name = "description", columnDefinition = "TEXT")
        private String description;

        @Column(name = "email", length = 255)
        private String email;

        @Column(name = "full_name", length = 255)
        private String fullName;

        @Column(name = "image", length = 255)
        private String image;

        @Column(name = "password", length = 128)
        private String password;

        @Column(name = "phone_number", length = 255)
        private String phoneNumber;

        @Column(name = "status")
        private Integer status;

        @ManyToOne
        @JoinColumn(name = "role_id")
        private Role role;

//        @OneToOne
//        @JoinColumn(name = "cv_id")
//        private CV cv;

        @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
        private CV cv;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private List<Company> companies;


        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<FollowCompany> followCompanies;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<SaveJob> savedJobs;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<ApplyPost> applyPosts;
        // Constructors, getters, and setters

        // Constructors
        public User() {
        }

        public User(String email, String fullName, String password, Role role) {
            this.email = email;
            this.fullName = fullName;
            this.password = password;
            this.role = role;
        }


        public List<ApplyPost> getApplyPosts() {
            return applyPosts;
        }

        public void setApplyPosts(List<ApplyPost> applyPosts) {
            this.applyPosts = applyPosts;
        }

        public List<SaveJob> getSavedJobs() {
            return savedJobs;
        }

        public void setSavedJobs(List<SaveJob> savedJobs) {
            this.savedJobs = savedJobs;
        }

        public List<FollowCompany> getFollowCompanies() {
            return followCompanies;
        }

        public void setFollowCompanies(List<FollowCompany> followCompanies) {
            this.followCompanies = followCompanies;
        }

        public List<Company> getCompanies() {
            return companies;
        }

        public void setCompanies(List<Company> companies) {
            this.companies = companies;
        }

        public CV getCv() {
            return cv;
        }

        public void setCv(CV cv) {
            this.cv = cv;
            if (cv != null) {
                cv.setUser(this); // Gán người dùng cho CV
            }
        }
//        public CV getCv() {
//            return cv;
//        }
//
//        public void setCv(CV cv) {
//            this.cv = cv;
//        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }
