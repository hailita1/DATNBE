package com.example.demo.service.bill;

import com.example.demo.model.Bill;
import com.example.demo.model.House;
import com.example.demo.model.auth.User;
import com.example.demo.service.IGeneralService;

import java.util.Date;
import java.util.List;

public interface IBillService extends IGeneralService<Bill> {
    Iterable<Bill> findByOrderByIdDesc();

    Iterable<Bill> findAllByHouseBillOrderByIdAsc(House house);

    void deleteListBill(List<Long> model);

    Iterable<Bill> findByStartDateGreaterThanEqualAndEndDateLessThanEqualAndStatus(Date sd, Date ed, String status);

    Iterable<Bill> findBillByUser(Long id, String status1, String status2, String status3);
}
