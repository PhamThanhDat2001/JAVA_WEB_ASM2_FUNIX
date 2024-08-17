package asm2.service;

import asm2.dao.SaveJobDAO;
import asm2.dao.UserDao;
import asm2.entity.Company;
import asm2.entity.Recruitment;
import asm2.entity.SaveJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SaveJobServiceImpl implements SaveJobService{
    @Autowired
    private SaveJobDAO saveJobDao;

    @Override
    @Transactional
    public void saveJob(SaveJob saveJob) {
        saveJobDao.saveJob(saveJob);
    }
    @Override
    @Transactional
    public boolean isJobSaved(int userId, int recruitId) {
        return saveJobDao.isJobSaved(userId, recruitId);
    }
    @Override
    @Transactional
    public void deleteSaveJob(int userId, int recruitmentId) {
        saveJobDao.deleteSaveJob(userId, recruitmentId);
    }


    @Override
    @Transactional
    public List<Recruitment> findAllRecruitmentFromSaveJob(Integer userId, int offset, int pageSize) {
        return saveJobDao.findAllRecruitmentFromSaveJob(userId,offset,pageSize);
    }
    @Override
    @Transactional
    public long countRecruitmentsByUserId(Integer userId) {
        return saveJobDao.countRecruitmentsByUserId(userId);
    }

    @Override
    @Transactional
    public List<Company> findAllCompaniesFromSaveJob(Integer userId) {
        return saveJobDao.findAllCompaniesFromSaveJob(userId);
    }
}
