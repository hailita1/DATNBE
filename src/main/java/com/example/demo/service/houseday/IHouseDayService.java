package com.example.demo.service.houseday;

import com.example.demo.model.House;
import com.example.demo.model.HouseDay;
import com.example.demo.service.IGeneralService;

import java.util.Date;

public interface IHouseDayService extends IGeneralService<HouseDay> {

    HouseDay findById(Date date);

    HouseDay save(HouseDay houseDay);

    void remove(Date date);

    Iterable<HouseDay> findAllByHouseDate(House house);
}
