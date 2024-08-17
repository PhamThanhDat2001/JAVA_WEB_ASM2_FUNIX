package asm2.dao;

import asm2.entity.Company;
import asm2.entity.Recruitment;
import asm2.entity.SaveJob;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaveJobDAO {
    public void saveJob(SaveJob saveJob);

    boolean isJobSaved(int userId, int recruitId);
    void deleteSaveJob(int userId, int recruitmentId);

//    public List<Recruitment> findAllRecruitmentFromSaveJob(Integer userId);
List<Recruitment> findAllRecruitmentFromSaveJob(Integer userId, int offset, int pageSize);
    long countRecruitmentsByUserId(Integer userId); // Phương thức để đếm số lượng Recruitment bởi userId
    public List<Company> findAllCompaniesFromSaveJob(Integer userId);
}
