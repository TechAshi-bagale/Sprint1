package com.serviceimpl;

import com.dao.Categorydao;
import com.daoimpl.CategoryDaoImpl;
import com.entity.Category;
import com.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private Categorydao categoryDAO = new CategoryDaoImpl();

    @Override
    public void addCategory(Category category) {
        categoryDAO.addCategory(category);
    }

    @Override
    public void updateCategory(Category category) {
        categoryDAO.updateCategory(category);
    }

    @Override
    public boolean deleteCategory(int id) {
        return categoryDAO.deleteCategory(id);
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryDAO.getCategoryById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }
}
