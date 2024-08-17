package asm2.dao;

import asm2.entity.Company;
import asm2.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CompanyDAOImpl implements CompanyDAO{
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void updateCompany(Company company) {
        Session session = sessionFactory.getCurrentSession();
        session.update(company);
    }

    @Override
    public Company findById(int companyId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Company.class, companyId);
    }


    @Override
    public Company saveCompany(Company company) {
        Session session = sessionFactory.getCurrentSession();
        session.save(company); // Lưu đối tượng company vào CSDL
        return company; // Trả về đối tượng company sau khi đã được lưu vào CSDL và có id mới
    }

    @Override
    public int countCompanies() {
        String sql = "SELECT COUNT(*) FROM company";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
