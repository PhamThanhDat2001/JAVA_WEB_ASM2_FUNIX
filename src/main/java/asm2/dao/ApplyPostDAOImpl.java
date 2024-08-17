package asm2.dao;

import asm2.entity.ApplyPost;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ApplyPostDAOImpl implements ApplyPostDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public int countApplyPosts() {
        String sql = "SELECT COUNT(*) FROM applypost";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public void saveApplyPost(ApplyPost applyPost) {
        Session session = sessionFactory.getCurrentSession();
        session.save(applyPost);
    }
}
