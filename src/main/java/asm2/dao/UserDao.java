package asm2.dao;

import asm2.entity.CV;
import asm2.entity.User;
import asm2.entity.UserRecruitmentInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao  {
    User findByEmailAndPassword(String email, String password);

    boolean isUserExists(String email);
    void saveUser(User user);

    public User findByEmail(String email);

    public User findById(int userId);

    public void updateUser(User user);

    public List<UserRecruitmentInfo> getUsersByCompany(int companyId);

    public List<UserRecruitmentInfo> getUsersByCompany(int companyId, int page, int pageSize);
    public int countUsersByCompany(int companyId);

    void saveCV(CV cv);
}
