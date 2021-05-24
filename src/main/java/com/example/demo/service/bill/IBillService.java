package com.example.demo.service.bill;

import com.example.demo.model.Bill;
import com.example.demo.model.House;
import com.example.demo.model.auth.User;
import com.example.demo.service.IGeneralService;

import java.util.List;

public interface IBillService extends IGeneralService<Bill> {
    Iterable<Bill> findByOrderByIdDesc();

    Iterable<Bill> findAllByUserAndStatusOrStatus(User user, String status1, String status2);

    Iterable<Bill> findAllByHouseBillOrderByIdAsc(House house);

    void deleteListBill(List<Long> model);
}
