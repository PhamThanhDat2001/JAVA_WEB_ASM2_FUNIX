package asm2.service;


import asm2.entity.CV;
import asm2.entity.Company;
import asm2.entity.User;
import asm2.entity.UserRecruitmentInfo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    User findByEmailAndPassword(String email, String password);

    boolean isUserExists(String email);
    void saveUser(User user);

    public User findByEmail(String email);

    public User findById(int userId);


//    public User getUserById(Integer userId);
//    public List<Company> getUserCompanies(Integer userId);

    public void updateUser(User user);

    public List<UserRecruitmentInfo> getUsersByCompany(int companyId);

    public List<UserRecruitmentInfo> getUsersByCompany(int companyId, int page, int pageSize);
    public int countUsersByCompany(int companyId);

    void saveCV(CV cv);
}
