package com.example.demo.service.category;

import com.example.demo.model.Category;
import com.example.demo.repository.ICategoryRepository;
import com.example.demo.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Categoryservice implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }


    @Override
    public void deleteListCatrgory(List<Long> model) {
        if (model != null) {
            for (int i = 0; i < model.size(); i++) {
                categoryRepository.deleteById(model.get(i));
            }
        }
    }

    @Override
    public Iterable<Category> findAllByStatusTrue() {
        return categoryRepository.findAllByStatusTrue();
    }
}
