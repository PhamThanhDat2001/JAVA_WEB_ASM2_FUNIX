package asm2.dao;

import asm2.entity.Category;
import asm2.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAPImpl implements CategoryDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Category findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Category.class, id);
    }
}
