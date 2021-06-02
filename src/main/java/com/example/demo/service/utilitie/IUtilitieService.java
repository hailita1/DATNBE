package com.example.demo.service.utilitie;

import com.example.demo.model.House;
import com.example.demo.model.Utilitie;
import com.example.demo.service.IGeneralService;

import java.util.List;

public interface IUtilitieService extends IGeneralService<Utilitie> {
    Utilitie findByName(String name);

    void deleteListService(List<Long> model);

    Iterable<Utilitie> findAllByStatusTrue();
}
