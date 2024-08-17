package asm2.config;

import asm2.entity.Category;
import asm2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements Converter<String, Category> {

    @Autowired
    private CategoryService categoryService;

    @Override
    public Category convert(String source) {
        if (source == null || source.isEmpty()) {
            return null;
        }
        Integer id = Integer.parseInt(source);
        return categoryService.findById(id);
    }
}
