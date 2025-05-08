package com.service;
import com.entity.Category;
import java.util.List;

public interface CategoryService {
    void addCategory(Category category);
    void updateCategory(Category category);
    boolean deleteCategory(int categoryId);
    Category getCategoryById(int categoryId);
    List<Category> getAllCategories();
}
