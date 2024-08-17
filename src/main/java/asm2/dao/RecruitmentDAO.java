package asm2.dao;

import asm2.entity.Category;
import asm2.entity.Recruitment;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

public interface RecruitmentDAO {
    public Map<String, Object> getTopRecruitment();
    public List<Map<String, Object>> getTopCategories();

    public List<Recruitment> findByCompanyId(Integer companyId);

    public List<Recruitment> findPaginatedRecruitments(int companyId, int start, int pageSize);
    public int countByCompanyId(int companyId);
    public void saveRecruit(Recruitment recruitment);

    public Recruitment findById(int id);

    List<Recruitment> findAll();

    public void deleteRecruitment(int id);

    int countRecruitments();

    List<Recruitment> searchJobs(String query);
    public List<Recruitment> searchJobsByCompanyName(String companyName);
    public List<Recruitment> searchJobsByLocation(String location);

    public boolean toggleFollowJob(Long recruitId);
    public boolean toggleFollowCompany(Long companyId);

}
