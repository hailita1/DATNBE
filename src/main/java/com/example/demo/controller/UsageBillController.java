package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.IUsageBillRepository;
import com.example.demo.service.houseday.IHouseDayService;
import com.example.demo.service.usageBill.IUsageBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/usageBill")
public class UsageBillController {
    @Autowired
    private IUsageBillService usageBillService;

    @Autowired
    private IUsageBillRepository usageBillRepository;

    @Autowired
    private IHouseDayService houseDayService;
    private long oneDay = 86400000;

    @GetMapping
    public ResponseEntity<Iterable<UsageBill>> getAllUsageBill() {
        return new ResponseEntity<>(usageBillService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity CreateUsageBill(@RequestBody UsageBill bill) {
        long startDate = bill.getStartDate().getTime();
        long endDateFinal = bill.getEndDateFinal().getTime();
        Iterable<HouseDay> listHouseDay = houseDayService.findAllByHouseDateAndDateBetween(bill.getHouseBill(), bill.getStartDate(), bill.getEndDate());
        for (HouseDay houseDay : listHouseDay) {
            houseDayService.remove(houseDay.getId());
        }
        for (long i = (startDate); i <= (endDateFinal); i += oneDay) {
            Date date1 = new Date(i);
            HouseDay houseDay = new HouseDay();
            houseDay.setDate(date1);
            houseDay.setStatus(true);
            houseDay.setHouseDate(bill.getHouseBill());
            houseDayService.save(houseDay);
        }
        usageBillService.save(bill);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsageBill> getUsageBill(@PathVariable Long id) {
        Optional<UsageBill> usageBillOptional = usageBillService.findById(id);
        return usageBillOptional.map(usageBill -> new ResponseEntity<>(usageBill, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsageBill> deleteUsageBill(@PathVariable Long id) {
        Optional<UsageBill> usageBillOptional = usageBillService.findById(id);
        return usageBillOptional.map(usageBill -> {
            usageBillService.remove(id);
            return new ResponseEntity<>(usageBill, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/total-price")
    public ResponseEntity<Long> sumTotalPrice(@RequestParam(name = "month") Integer month, @RequestParam(name = "year") Integer year, @RequestParam(name = "id") Long id) {
        return new ResponseEntity<>(usageBillRepository.sumTotalPrice(month, year, id), HttpStatus.OK);
    }

    @GetMapping("/total-price-by-house")
    public ResponseEntity<Long> sumTotalPriceByHouse(@RequestParam(name = "month") Integer month,
                                                     @RequestParam(name = "year") Integer year,
                                                     @RequestParam(name = "idHost") Long idHost,
                                                     @RequestParam(name = "idHouse") Long idHouse) {
        return new ResponseEntity<>(usageBillRepository.sumTotalPriceHomeStay(month, year, idHost, idHouse), HttpStatus.OK);
    }
}
