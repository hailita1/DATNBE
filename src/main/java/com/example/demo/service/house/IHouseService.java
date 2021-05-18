package com.example.demo.service.house;

import com.example.demo.model.Bill;
import com.example.demo.model.Category;
import com.example.demo.model.House;
import com.example.demo.model.Utilitie;
import com.example.demo.model.auth.User;
import com.example.demo.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IHouseService extends IGeneralService<House> {
    Iterable<House> findAllByCategory(Category category);

    Iterable<House> findAllByUser(User user);

    void deleteListService(List<Long> model);

    Iterable<House> findByStatusTrueOrderByIdDesc();

    Iterable<House> findAllByNameContaining(String name);

    Iterable<House> findByOrderByIdDesc();

    Iterable<House> findByStatusTrueOrderByNumberHiresDesc();

    Iterable<House> findByStatusTrueOrderByDiscountDesc();

    Iterable<House> findAllByAddressContaining(String address);

    Iterable<House> findAllByAddressContainsAndNumberRoomLessThanEqualAndPriceBetweenAndStatusTrue(String address, Long numberRoom, Double lowerBound, Double upperBound);

    Iterable<House> findAllByAddressContainsAndNumberRoomLessThanEqualAndStatusTrueOrPriceBetween(String address, Long numberRoom, Double lowerBound, Double upperBound);

    Iterable<House> findAllByAddressContainsAndPriceBetweenAndStatusTrue(String address, Double lowerBound, Double upperBound);
}
