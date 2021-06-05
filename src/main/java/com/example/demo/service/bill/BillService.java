package com.example.demo.service.bill;

import com.example.demo.model.Bill;
import com.example.demo.model.House;
import com.example.demo.model.auth.User;
import com.example.demo.repository.IBillRepository;
import com.example.demo.repository.IServiceRepository;
import com.example.demo.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BillService implements IBillService {
    private IBillRepository billRepository;
    private IServiceRepository serviceRepository;

    public BillService(IBillRepository billRepository, IServiceRepository serviceRepository) {
        this.billRepository = billRepository;
        this.serviceRepository = serviceRepository;
    }

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
    public Iterable<Bill> findAllByHouseBillOrderByIdAsc(House house) {
        return billRepository.findAllByHouseBillOrderByIdAsc(house);
    }

    @Override
    public void deleteListBill(List<Long> model) {
        if (model != null) {
            for (int i = 0; i < model.size(); i++) {
                billRepository.deleteById(model.get(i));
            }
        }
    }


    @Override
    public Iterable<Bill> findBillByUser(Long id, String status1, String status2, String status3) {
        return billRepository.findBillByUser(id, status1, status2, status3);
    }

    @Transactional
    public ResponseEntity<Object> updateBillService(Bill bill, Long id) {
        if (billRepository.findById(id).isPresent()) {
            Bill newBill = billRepository.findById(id).get();
            newBill.setNameUser(bill.getNameUser());
            newBill.setEmail(bill.getEmail());
            newBill.setService(bill.getService());
            Bill savedUser = billRepository.save(newBill);
            if (billRepository.findById(savedUser.getId()).isPresent())
                return ResponseEntity.accepted().body("User updated successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed updating the user specified");
        } else return ResponseEntity.unprocessableEntity().body("Cannot find the user specified");
    }
}
