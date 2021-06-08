package com.example.demo.service.houseday;

import com.example.demo.model.House;
import com.example.demo.model.HouseDay;
import com.example.demo.repository.IHouseDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class HouseDayService implements IHouseDayService {
    @Autowired
    private IHouseDayRepository dateRepository;

    @Override
    public Iterable<HouseDay> findAll() {
        return dateRepository.findAll();
    }

    @Override
    public HouseDay save(HouseDay houseDay) {
        return dateRepository.save(houseDay);
    }

    @Override
    public Iterable<HouseDay> findAllByHouseDate(House house) {
        return dateRepository.findAllByHouseDate(house);
    }

    @Override
    public Iterable<HouseDay> findAllByHouseDateAndDateBetween(House house, Date startDate, Date endDate) {
        return dateRepository.findAllByHouseDateAndDateBetween(house, startDate, endDate);
    }

    @Override
    public Iterable<HouseDay> findAllByHouseDateAndDateGreaterThan(House house, Date endDate) {
        return dateRepository.findAllByHouseDateAndDateGreaterThan(house, endDate);
    }

    @Override
    public Optional<HouseDay> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void remove(Long id) {
        dateRepository.deleteById(id);
    }
}
