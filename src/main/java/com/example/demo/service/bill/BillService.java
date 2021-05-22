package com.example.demo.service.bill;

import com.example.demo.model.Bill;
import com.example.demo.model.House;
import com.example.demo.model.auth.User;
import com.example.demo.repository.IBillRepository;
import com.example.demo.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService implements IBillService {
    @Autowired
    private IBillRepository billRepository;

    @Override
    public Iterable<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public Optional<Bill> findById(Long id) {
        return billRepository.findById(id);
    }

    @Override
    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public void remove(Long id) {
        billRepository.deleteById(id);
    }


    @Override
    public Iterable<Bill> findByOrderByIdDesc() {
        return billRepository.findByOrderByIdDesc();
    }

    @Override
    public Iterable<Bill> findAllByUserAndStatusOrStatus(User user, String status1, String status2) {
        return billRepository.findAllByUserAndStatusOrStatus(user, status1, status2);
    }

    @Override
    public Iterable<Bill> findAllByHouseBillOrderByIdDesc(House house) {
        return billRepository.findAllByHouseBillOrderByIdDesc(house);
    }

    @Override
    public void deleteListBill(List<Long> model) {
        if (model != null) {
            for (int i = 0; i < model.size(); i++) {
                billRepository.deleteById(model.get(i));
            }
        }
    }
}