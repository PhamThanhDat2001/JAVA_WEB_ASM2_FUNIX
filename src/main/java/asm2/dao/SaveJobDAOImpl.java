package asm2.dao;

import asm2.entity.Company;
import asm2.entity.Recruitment;
import asm2.entity.SaveJob;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SaveJobDAOImpl implements SaveJobDAO{
    @Autowired
    private SessionFactory sessionFactory;

    //    sử dụng JdbcTemplate để thực hiện các thao tác cơ sở dữ liệu
//    giúp đơn giản hóa việc tương tác với cơ sở dữ liệu bằng JDBC
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void saveJob(SaveJob saveJob) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(saveJob);
    }

    @Override
    public boolean isJobSaved(int userId, int recruitId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Long> query = session.createQuery(
                "SELECT COUNT(sj.id) FROM SaveJob sj WHERE sj.user.id = :userId AND sj.recruitment.id = :recruitId",
                Long.class
        );
        query.setParameter("userId", userId);
        query.setParameter("recruitId", recruitId);
        Long count = query.uniqueResult();
        return count != null && count > 0;
    }
    @Override
    public void deleteSaveJob(int userId, int recruitmentId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE FROM SaveJob s WHERE s.user.id = :userId AND s.recruitment.id = :recruitmentId");
        query.setParameter("userId", userId);
        query.setParameter("recruitmentId", recruitmentId);
        query.executeUpdate();
    }



//    public List<Company> findAllCompaniesFromSaveJob() {
//        Session session = sessionFactory.getCurrentSession();
//        String hql = "SELECT c FROM SaveJob sj JOIN sj.recruitment r JOIN r.company c";
//        Query<Company> query = session.createQuery(hql, Company.class);
//        return query.getResultList();
//    }
    @Override
    public List<Company> findAllCompaniesFromSaveJob(Integer userId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT c FROM SaveJob sj JOIN sj.recruitment r JOIN r.company c WHERE sj.user.id = :userId";
        Query<Company> query = session.createQuery(hql, Company.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

@Override
public List<Recruitment> findAllRecruitmentFromSaveJob(Integer userId, int offset, int pageSize) {
    Session session = sessionFactory.getCurrentSession();
    String hql = "SELECT r FROM SaveJob sj JOIN sj.recruitment r WHERE sj.user.id = :userId";
    Query<Recruitment> query = session.createQuery(hql, Recruitment.class);
    query.setParameter("userId", userId);
    query.setFirstResult(offset);
    query.setMaxResults(pageSize);
    return query.getResultList();
}



    @Override
    public long countRecruitmentsByUserId(Integer userId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Long> query = session.createQuery(
                "SELECT COUNT(r.id) FROM SaveJob sj JOIN sj.recruitment r WHERE sj.user.id = :userId",
                Long.class
        );
        query.setParameter("userId", userId);
        return query.uniqueResult();
    }
}
