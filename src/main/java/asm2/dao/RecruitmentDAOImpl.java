package asm2.dao;

import asm2.entity.Category;
import asm2.entity.Recruitment;
import asm2.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class RecruitmentDAOImpl implements RecruitmentDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // lấy ra logo, tên công ty, vị trí ứng tuyển nhiều nhất
//    public Map<String, Object> getTopRecruitment() {
//        String sql = "SELECT recruitment.title, company.logo,company.name_company,company.address,recruitment.`type`," +
//                " COUNT(applypost.id) AS num_applications " +
//                     "FROM applypost " +
//                     "JOIN recruitment ON applypost.recruitment_id = recruitment.id " +
//                     "JOIN company ON recruitment.company_id = company.id " +
//                     "GROUP BY recruitment.title, company.logo,company.name_company,company.address,recruitment.`type` " +
//                     "ORDER BY num_applications DESC " +
//                     "LIMIT 1";
//
//        return jdbcTemplate.queryForMap(sql);
//    }
    public Map<String, Object> getTopRecruitment() {
        String sql = "SELECT recruitment.id, recruitment.title, company.logo, company.name_company, company.address, recruitment.`type`, " +
                "COUNT(applypost.id) AS num_applications " +
                "FROM applypost " +
                "JOIN recruitment ON applypost.recruitment_id = recruitment.id " +
                "JOIN company ON recruitment.company_id = company.id " +
                "GROUP BY recruitment.id, recruitment.title, company.logo, company.name_company, company.address, recruitment.`type` " +
                "ORDER BY num_applications DESC " +
                "LIMIT 1";

        return jdbcTemplate.queryForMap(sql);
    }

    // Lấy ra danh sách top 4 danh mục công việc có số lượng tuyển dụng nhiều nhất
    public List<Map<String, Object>> getTopCategories() {
        String sql = "SELECT category.name, COUNT(recruitment.id) AS num_recruitments " +
                "FROM category " +
                "LEFT JOIN recruitment ON category.id = recruitment.category_id " +
                "GROUP BY category.id " +
                "ORDER BY num_recruitments DESC " +
                "LIMIT 4";

        return jdbcTemplate.queryForList(sql);
    }


    // Phương thức để lấy danh sách Recruitment theo company_id bằng SessionFactory
    public List<Recruitment> findByCompanyId(Integer companyId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT r FROM Recruitment r JOIN FETCH r.company WHERE r.company.id = :companyId";
        return session.createQuery(hql, Recruitment.class)
                .setParameter("companyId", companyId)
                .getResultList();
    }


    @Override
    public List<Recruitment> findPaginatedRecruitments(int companyId, int start, int pageSize) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Recruitment r WHERE r.company.id = :companyId";
        TypedQuery<Recruitment> query = session.createQuery(hql, Recruitment.class);
        query.setParameter("companyId", companyId);
        query.setFirstResult(start);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public int countByCompanyId(int companyId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT COUNT(r) FROM Recruitment r WHERE r.company.id = :companyId";
        TypedQuery<Long> query = session.createQuery(hql, Long.class);
        query.setParameter("companyId", companyId);
        return query.getSingleResult().intValue();
    }

//    public void saveRecruit(Recruitment recruitment) {
//        Session session = sessionFactory.getCurrentSession();
//        session.save(recruitment);
//    }
    public void saveRecruit(Recruitment recruitment) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(recruitment);
    }

    @Override
    public Recruitment findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Recruitment.class, id);
    }

    @Override
    public List<Recruitment> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Recruitment", Recruitment.class).getResultList();
    }

    @Override
    public void deleteRecruitment(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query theQuery =
                currentSession.createQuery("delete from Recruitment where id=:recruitmentId");
        theQuery.setParameter("recruitmentId",id);
        theQuery.executeUpdate();
    }

    @Override
    public int countRecruitments() {
        String sql = "SELECT COUNT(*) FROM recruitment";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public List<Recruitment> searchJobs(String query) {
        Session session = sessionFactory.getCurrentSession();
//        String hql = "FROM Recruitment r WHERE r.title LIKE :query OR r.description LIKE :query";
        String hql = "FROM Recruitment r WHERE r.title LIKE :query";
        TypedQuery<Recruitment> typedQuery = session.createQuery(hql, Recruitment.class);
        typedQuery.setParameter("query", "%" + query + "%");
        return typedQuery.getResultList();
    }

    @Override
    public List<Recruitment> searchJobsByCompanyName(String query) {
        Session session = sessionFactory.getCurrentSession();
//        String hql = "FROM Recruitment r WHERE r.title LIKE :query OR r.description LIKE :query";
        String hql = "SELECT r, c FROM Recruitment r INNER JOIN r.company c WHERE c.nameCompany LIKE :query";
        Query query1 = session.createQuery(hql);
        query1.setParameter("query", "%" + query + "%");
        List<Object[]> results = query1.getResultList();

// Process results as needed
        List<Recruitment> recruitments = new ArrayList<>();
        for (Object[] result : results) {
            Recruitment recruitment = (Recruitment) result[0]; // Assuming Recruitment is the first entity selected
            // Other processing if needed
            recruitments.add(recruitment);
        }
        return recruitments;
    }
    @Override
    public List<Recruitment> searchJobsByLocation(String query) {
        Session session = sessionFactory.getCurrentSession();
//        String hql = "FROM Recruitment r WHERE r.title LIKE :query OR r.description LIKE :query";
        String hql = "FROM Recruitment r WHERE r.address LIKE :query";
        TypedQuery<Recruitment> typedQuery = session.createQuery(hql, Recruitment.class);
        typedQuery.setParameter("query", "%" + query + "%");
        return typedQuery.getResultList();
    }



    @Override
    public boolean toggleFollowJob(Long recruitId) {
        String selectQuery = "SELECT COUNT(*) FROM follow WHERE recruit_id = ?";
        int count = jdbcTemplate.queryForObject(selectQuery, new Object[]{recruitId}, Integer.class);
        if (count > 0) {
            String deleteQuery = "DELETE FROM follow WHERE recruit_id = ?";
            jdbcTemplate.update(deleteQuery, recruitId);
            return false;
        } else {
            String insertQuery = "INSERT INTO follow (recruit_id) VALUES (?)";
            jdbcTemplate.update(insertQuery, recruitId);
            return true;
        }
    }

    @Override
    public boolean toggleFollowCompany(Long companyId) {
        String selectQuery = "SELECT COUNT(*) FROM follow WHERE company_id = ?";
        int count = jdbcTemplate.queryForObject(selectQuery, new Object[]{companyId}, Integer.class);
        if (count > 0) {
            String deleteQuery = "DELETE FROM follow WHERE company_id = ?";
            jdbcTemplate.update(deleteQuery, companyId);
            return false;
        } else {
            String insertQuery = "INSERT INTO follow (company_id) VALUES (?)";
            jdbcTemplate.update(insertQuery, companyId);
            return true;
        }
    }
}
