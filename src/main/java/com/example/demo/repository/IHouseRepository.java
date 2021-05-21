package com.example.demo.repository;

import com.example.demo.model.Category;
import com.example.demo.model.House;
import com.example.demo.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHouseRepository extends JpaRepository<House, Long> {
    Iterable<House> findAllByCategory(Category category);

    Iterable<House> findAllByUser(User user);

    Iterable<House> findByStatusTrueOrderByIdDesc();

    Iterable<House> findByStatusTrueOrderByDiscountDesc();

    Iterable<House> findByOrderByIdDesc();

    Iterable<House> findAllByNameContaining(String name);

    Iterable<House> findAllByAddressContaining(String address);

    Iterable<House> findByStatusTrueOrderByNumberHiresDesc();

    Iterable<House> findAllByAddressContainsAndNumberRoomLessThanEqualAndPriceBetweenAndStatusTrue(String address, Long numberRoom, Double lowerBound, Double upperBound);

    Iterable<House> findAllByAddressContainsAndNumberRoomLessThanEqualAndStatusTrueOrPriceBetween(String address, Long numberRoom, Double lowerBound, Double upperBound);

    Iterable<House> findAllByAddressContainsAndPriceBetweenAndStatusTrue(String address, Double lowerBound, Double upperBound);

    //Danh sách nhà đang cho thuê giá giảm dần
    Iterable<House> findByStatusTrueOrderByPriceDesc();

    //Danh sách nhà đang cho thuê giá tăng dần
    Iterable<House> findByStatusTrueOrderByPriceAsc();
}
