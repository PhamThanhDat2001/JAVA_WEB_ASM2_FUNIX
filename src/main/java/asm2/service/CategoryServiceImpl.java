package asm2.service;

import asm2.dao.CategoryDAO;
import asm2.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService{
    // Giả sử bạn có một `CategoryRepository`
    @Autowired
    private CategoryDAO categoryDAO;


    @Override
    @Transactional
    public Category findById(int id) {
        return categoryDAO.findById(id);
    }
}
