package asm2.service;

import asm2.entity.Company;
import asm2.entity.Recruitment;
import asm2.entity.SaveJob;

import java.util.List;

public interface SaveJobService {
    public void saveJob(SaveJob saveJob);

    boolean isJobSaved(int userId, int recruitId);
    void deleteSaveJob(int userId, int recruitmentId);


List<Recruitment> findAllRecruitmentFromSaveJob(Integer userId, int offset, int pageSize);
    long countRecruitmentsByUserId(Integer userId); // Phương thức để đếm số lượng Recruitment bởi userId
    public List<Company> findAllCompaniesFromSaveJob(Integer userId);

}
