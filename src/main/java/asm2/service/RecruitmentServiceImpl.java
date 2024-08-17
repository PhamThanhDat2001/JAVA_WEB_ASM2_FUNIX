package asm2.service;

import asm2.dao.RecruitmentDAO;
import asm2.entity.Recruitment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class RecruitmentServiceImpl implements RecruitmentService{
    @Autowired
    RecruitmentDAO recruitmentDAO;
    @Override
    public Map<String, Object> getTopRecruitment() {
        return recruitmentDAO.getTopRecruitment() ;
    }

    @Override
    public List<Map<String, Object>> getTopCategories() {
        return recruitmentDAO.getTopCategories();
    }

    @Override
    @Transactional
    public List<Recruitment> findByCompanyId(Integer companyId) {
        return recruitmentDAO.findByCompanyId(companyId);
    }

    @Override
    @Transactional
    public List<Recruitment> findPaginatedRecruitments(int companyId, int start, int pageSize) {
        return recruitmentDAO.findPaginatedRecruitments(companyId,start,pageSize);
    }

    @Override
    @Transactional
    public int countByCompanyId(int companyId) {
        return recruitmentDAO.countByCompanyId(companyId);
    }

    @Override
    @Transactional
    public void saveRecruit(Recruitment recruitment) {
        recruitmentDAO.saveRecruit(recruitment);
    }

    @Override
    @Transactional
    public Recruitment findById(int id) {
       return recruitmentDAO.findById(id);
    }
    @Override
    @Transactional
    public List<Recruitment> findAll() {
        return recruitmentDAO.findAll();
    }

    @Override
    @Transactional
    public void deleteRecruitment(int id) {
        recruitmentDAO.deleteRecruitment(id);
    }

    @Override
    public int countRecruitments() {
        return recruitmentDAO.countRecruitments();
    }

    @Override
    @Transactional
    public List<Recruitment> searchJobs(String query) {
        return recruitmentDAO.searchJobs(query);
    }

    @Override
    @Transactional
    public List<Recruitment> searchJobsByCompanyName(String companyName) {
        return recruitmentDAO.searchJobsByCompanyName(companyName);
    }

    @Override
    @Transactional
    public List<Recruitment> searchJobsByLocation(String location) {
        return recruitmentDAO.searchJobsByLocation(location);
    }

    @Override
    public boolean toggleFollowJob(Long recruitId) {
        return recruitmentDAO.toggleFollowJob(recruitId);
    }

    @Override
    public boolean toggleFollowCompany(Long companyId) {
        return recruitmentDAO.toggleFollowCompany(companyId);
    }

}
