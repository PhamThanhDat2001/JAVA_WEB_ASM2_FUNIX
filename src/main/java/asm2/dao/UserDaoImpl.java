package asm2.dao;


import asm2.entity.CV;
import asm2.entity.User;
import asm2.entity.UserRecruitmentInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    private SessionFactory sessionFactory;

//    sử dụng JdbcTemplate để thực hiện các thao tác cơ sở dữ liệu
//    giúp đơn giản hóa việc tương tác với cơ sở dữ liệu bằng JDBC
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public User findByEmailAndPassword(String email, String password) {
        Session currentSession = sessionFactory.getCurrentSession();

        // Tạo một câu truy vấn sử dụng HQL (Hibernate Query Language)
        String hql = "FROM User WHERE email = :email AND password = :password";
        Query<User> query = currentSession.createQuery(hql, User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);

        // Lấy danh sách kết quả (danh sách User)
        List<User> results = query.getResultList();

        // Kiểm tra xem có kết quả nào hay không
        if (!results.isEmpty()) {
            // Nếu có kết quả, trả về user đầu tiên trong danh sách (do email là duy nhất)
            return results.get(0);
        } else {
            // Nếu không có kết quả, trả về null
            return null;
        }
    }


    @Override
    public boolean isUserExists(String email) {
        Session session = sessionFactory.getCurrentSession();

        // Tạo câu truy vấn HQL để lấy danh sách người dùng với email nhất định
        String hql = "FROM User WHERE email = :email";
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("email", email);

        // Lấy danh sách kết quả
        List<User> results = query.getResultList();

        // Kiểm tra xem danh sách kết quả có rỗng hay không
        return !results.isEmpty(); // Trả về true nếu danh sách không rỗng (người dùng tồn tại), ngược lại trả về false
    }


    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByEmail(String email) {
        // Lấy session hiện tại từ SessionFactory
        Session session = sessionFactory.getCurrentSession();

        // Tạo câu truy vấn HQL để tìm kiếm user bằng email
        Query<User> query = session.createQuery("from User where email = :email", User.class);
        query.setParameter("email", email);

        // Thực thi câu truy vấn và trả về kết quả
        return query.uniqueResult();
    }

    @Override
    public User findById(int userId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, userId);
    }


    public List<UserRecruitmentInfo> getUsersByCompany(int companyId) {
        String sql = "SELECT u.id AS user_id, u.full_name, u.email, u.phone_number, " +
                "r.title AS recruitment_title, r.address AS recruitment_address, " +
                "ap.created_at AS application_date, ap.status AS application_status " +
                "FROM user u " +
                "JOIN applypost ap ON u.id = ap.user_id " +
                "JOIN recruitment r ON ap.recruitment_id = r.id " +
                "WHERE r.company_id = ?";

        return jdbcTemplate.query(sql, new Object[]{companyId}, (rs, rowNum) ->
                new UserRecruitmentInfo(
                        rs.getLong("user_id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("recruitment_title"),
                        rs.getString("recruitment_address"),
                        rs.getString("application_date"),
                        rs.getInt("application_status")
                ));
    }



    public List<UserRecruitmentInfo> getUsersByCompany(int companyId, int page, int pageSize) {
        int offset = (page - 1) * pageSize; // Tính offset (vị trí bắt đầu của bản ghi trong trang)

        String sql = "SELECT u.id AS user_id, u.full_name, u.email, u.phone_number, " +
                "r.title AS recruitment_title, r.address AS recruitment_address, " +
                "ap.created_at AS application_date, ap.status AS application_status " +
                "FROM user u " +
                "JOIN applypost ap ON u.id = ap.user_id " +
                "JOIN recruitment r ON ap.recruitment_id = r.id " +
                "WHERE r.company_id = ? " +
                "LIMIT ? OFFSET ?";

        return jdbcTemplate.query(sql, new Object[]{companyId, pageSize, offset}, (rs, rowNum) ->
                new UserRecruitmentInfo(
                        rs.getLong("user_id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("recruitment_title"),
                        rs.getString("recruitment_address"),
                        rs.getString("application_date"),
                        rs.getInt("application_status")
                ));
    }

    public int countUsersByCompany(int companyId) {
        String sql = "SELECT COUNT(*) FROM user u " +
                "JOIN applypost ap ON u.id = ap.user_id " +
                "JOIN recruitment r ON ap.recruitment_id = r.id " +
                "WHERE r.company_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, companyId);
    }

    @Override
    public void saveCV(CV cv) {
        Session session = sessionFactory.getCurrentSession();
        session.save(cv);
    }

}
