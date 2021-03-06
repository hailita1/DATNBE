package com.example.demo.controller;

import com.example.demo.model.Bill;
import com.example.demo.model.House;
import com.example.demo.model.HouseDay;
import com.example.demo.service.houseday.IHouseDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/housedays")
public class HouseDayController {
    @Autowired
    private IHouseDayService houseDayService;

    @GetMapping
    public ResponseEntity<Iterable<HouseDay>> getAllHouseDay() {
        return new ResponseEntity<>(houseDayService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HouseDay> createNewHouseDay(@RequestBody HouseDay houseDay) {
        return new ResponseEntity<>(houseDayService.save(houseDay), HttpStatus.OK);
    }

    @PostMapping("/listHouseDay")
    public ResponseEntity<Iterable<HouseDay>> findByHouse(@RequestBody House house) {
        Iterable<HouseDay> houseDays = houseDayService.findAllByHouseDate(house);
        if (houseDays == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(houseDays, HttpStatus.OK);
    }

    @PostMapping("/listHouseDayBill")
    public ResponseEntity<Iterable<HouseDay>> findByHouseBill(@RequestBody Bill bill) {
        Iterable<HouseDay> houseDays = houseDayService.findAllByHouseDateAndDateGreaterThan(bill.getHouseBill(), bill.getEndDate());
        if (houseDays == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(houseDays, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<HouseDay> getHouseDay(@PathVariable Long id) {
        Optional<HouseDay> categoryOptional = houseDayService.findById(id);
        return categoryOptional.map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HouseDay> updateHouseDay(@PathVariable Long id, @RequestBody HouseDay houseDay) {
        Optional<HouseDay> houseDayOptional = houseDayService.findById(id);
        return houseDayOptional.map(houseDay1 -> {
            houseDay1.setId(houseDay1.getId());
            houseDay1.setHouseDate(houseDay.getHouseDate());
            houseDay1.setDate(houseDay.getDate());
            houseDay1.setStatus(houseDay.getStatus());
            return new ResponseEntity<>(houseDayService.save(houseDay1), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HouseDay> deleteHouseDay(@PathVariable Long id) {
        Optional<HouseDay> houseDayOptional = houseDayService.findById(id);
        return houseDayOptional.map(category -> {
            houseDayService.remove(id);
            return new ResponseEntity<>(category, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
