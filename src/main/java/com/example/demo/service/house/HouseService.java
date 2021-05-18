package com.example.demo.service.house;

import com.example.demo.model.*;
import com.example.demo.model.auth.User;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class HouseService implements IHouseService {
    @Autowired
    private IHouseRepository houseRepository;

    @Override
    public Iterable<House> findAllByCategory(Category category) {
        return houseRepository.findAllByCategory(category);
    }

    @Override
    public Iterable<House> findAllByUser(User user) {
        return houseRepository.findAllByUser(user);
    }

    @Override
    public void deleteListService(List<Long> model) {
        if (model != null) {
            for (int i = 0; i < model.size(); i++) {
                houseRepository.deleteById(model.get(i));
            }
        }
    }

    @Override
    public Iterable<House> findByStatusTrueOrderByIdDesc() {
        return houseRepository.findByStatusTrueOrderByIdDesc();
    }

    @Override
    public Iterable<House> findAllByNameContaining(String name) {
        return houseRepository.findAllByNameContaining(name);
    }

    @Override
    public Iterable<House> findByOrderByIdDesc() {
        return houseRepository.findByOrderByIdDesc();
    }

    @Override
    public Iterable<House> findByStatusTrueOrderByNumberHiresDesc() {
        return houseRepository.findByStatusTrueOrderByNumberHiresDesc();
    }

    @Override
    public Iterable<House> findByStatusTrueOrderByDiscountDesc() {
        return houseRepository.findByStatusTrueOrderByDiscountDesc();
    }

    @Override
    public Iterable<House> findAllByAddressContaining(String address) {
        return houseRepository.findAllByAddressContaining(address);
    }

    @Override
    public Iterable<House> findAllByAddressContainsAndNumberRoomLessThanEqualAndPriceBetweenAndStatusTrue(String address, Long numberRoom, Double lowerBound, Double upperBound) {
        return houseRepository.findAllByAddressContainsAndNumberRoomLessThanEqualAndPriceBetweenAndStatusTrue(address, numberRoom, lowerBound, upperBound);
    }

    @Override
    public Iterable<House> findAllByAddressContainsAndNumberRoomLessThanEqualAndStatusTrueOrPriceBetween(String address, Long numberRoom, Double lowerBound, Double upperBound) {
        return houseRepository.findAllByAddressContainsAndNumberRoomLessThanEqualAndStatusTrueOrPriceBetween(address, numberRoom, lowerBound, upperBound);
    }

    @Override
    public Iterable<House> findAllByAddressContainsAndPriceBetweenAndStatusTrue(String address, Double lowerBound, Double upperBound) {
        return houseRepository.findAllByAddressContainsAndPriceBetweenAndStatusTrue(address, lowerBound, upperBound);
    }


    @Override
    public Iterable<House> findAll() {
        return houseRepository.findAll();
    }

    @Override
    public Optional<House> findById(Long id) {
        return houseRepository.findById(id);
    }

    @Override
    public House save(House house) {
        return houseRepository.save(house);
    }

    @Override
    public void remove(Long id) {
        houseRepository.deleteById(id);
    }
}
