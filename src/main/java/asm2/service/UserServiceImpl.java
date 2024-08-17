package asm2.service;



import asm2.dao.UserDao;
import asm2.entity.CV;
import asm2.entity.Company;
import asm2.entity.User;
import asm2.entity.UserRecruitmentInfo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public User findByEmailAndPassword(String email, String password) {
        return userDao.findByEmailAndPassword(email, password);
    }

    @Override
    @Transactional
    public boolean isUserExists(String email) {
        return userDao.isUserExists(email);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    @Transactional
    public User findById(int userId) {
        return userDao.findById(userId);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public List<UserRecruitmentInfo> getUsersByCompany(int companyId) {
        return userDao.getUsersByCompany(companyId);
    }

    @Override
    @Transactional
    public List<UserRecruitmentInfo> getUsersByCompany(int companyId, int page, int pageSize) {
        return userDao.getUsersByCompany(companyId, page, pageSize);
    }

    @Override
    public int countUsersByCompany(int companyId) {
        return userDao.countUsersByCompany(companyId);
    }

    @Override
    @Transactional
    public void saveCV(CV cv) {
        userDao.saveCV(cv);
    }


}
