package asm2.entity;

public class UserRecruitmentInfo {
    private String applicationText;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String recruitmentTitle;
    private String recruitmentAddress;
    private String applicationDate;
    private int applicationStatus;

    // Constructors, getters, and setters

    public UserRecruitmentInfo(long userId, String fullName, String email, String phoneNumber,
                               String recruitmentTitle, String recruitmentAddress,
                               String applicationDate, int applicationStatus) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.recruitmentTitle = recruitmentTitle;
        this.recruitmentAddress = recruitmentAddress;
        this.applicationDate = applicationDate;
        this.applicationStatus = applicationStatus;
    }


    public UserRecruitmentInfo(Long userId, String fullName, String email, String phoneNumber,
                               String recruitmentTitle, String recruitmentAddress,
                               String applicationDate, Integer applicationStatus, String applicationText) {
        // Khởi tạo các thuộc tính của đối tượng từ các đối số được truyền vào

        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.recruitmentTitle = recruitmentTitle;
        this.recruitmentAddress = recruitmentAddress;
        this.applicationDate = applicationDate;
        this.applicationStatus = applicationStatus;
        this.applicationText = applicationText; // Đặt giá trị cho trường text
    }

    // Getters and setters
    // You can generate these using your IDE or write them manually

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRecruitmentTitle() {
        return recruitmentTitle;
    }

    public void setRecruitmentTitle(String recruitmentTitle) {
        this.recruitmentTitle = recruitmentTitle;
    }

    public String getRecruitmentAddress() {
        return recruitmentAddress;
    }

    public void setRecruitmentAddress(String recruitmentAddress) {
        this.recruitmentAddress = recruitmentAddress;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public int getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(int applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getApplicationText() {
        return applicationText;
    }

    public void setApplicationText(String applicationText) {
        this.applicationText = applicationText;
    }
}
