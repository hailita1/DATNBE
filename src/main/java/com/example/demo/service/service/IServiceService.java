package com.example.demo.service.service;

import com.example.demo.model.Service;
import com.example.demo.service.IGeneralService;

import java.util.List;

public interface IServiceService extends IGeneralService<Service> {
    void deleteListService(List<Long> model);

    Iterable<Service> findAllByStatusTrue();

    Service findByName(String name);
}
