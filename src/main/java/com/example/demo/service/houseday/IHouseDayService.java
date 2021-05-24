package com.example.demo.service.houseday;

import com.example.demo.model.House;
import com.example.demo.model.HouseDay;
import com.example.demo.service.IGeneralService;

import java.util.Date;

public interface IHouseDayService extends IGeneralService<HouseDay> {

    HouseDay save(HouseDay houseDay);

    Iterable<HouseDay> findAllByHouseDate(House house);

    Iterable<HouseDay> findAllByHouseDateAndDateBetween(House house, Date startDate, Date endDate);
}
