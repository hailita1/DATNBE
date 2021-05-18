package com.example.demo.service.category;

import com.example.demo.model.Category;
import com.example.demo.service.IGeneralService;

import java.util.ArrayList;
import java.util.List;

public interface ICategoryService extends IGeneralService<Category> {
    void deleteListCatrgory(List<Long> model);

    Iterable<Category> findAllByStatusTrue();
}
