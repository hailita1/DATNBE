package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.model.auth.User;
import com.example.demo.service.EmailService;
import com.example.demo.service.bill.IBillService;
import com.example.demo.service.houseday.IHouseDayService;
import com.example.demo.service.image.ImageService;
import com.example.demo.service.service.IServiceService;
import com.example.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/bills")
public class BillController {
    @Autowired
    private IBillService billService;

    @Autowired
    private UserService userService;

    @Autowired
    private IServiceService serviceService;

    @Autowired
    private IHouseDayService houseDayService;

    @Autowired
    private EmailService emailService;

    private String TEXT_WAIT_FOR_CONFIRMATION = "Chờ khách hàng xác nhận";
    private String TEXT_HOST_CONFIRMETION = "Chờ chủ nhà xác nhận";
    private String TEXT_HIRING = "Đã thuê thành công";
    private String TEXT_CANCELLATION = "Đơn đặt đã bị hủy";
    private String TEXT_PAID = "Đã thanh toán";
    private long oneDay = 86400000;

    @GetMapping
    public ResponseEntity<Iterable<Bill>> getAllBill() {
        return new ResponseEntity<>(billService.findByOrderByIdDesc(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createNewBill(@RequestBody Bill bill) {
        if (bill.getUser() != null) {
            emailService.sendEmail(bill.getEmail(), "Xác nhận đặt thuê HomeStay", "Hãy ấn vào link sau để xác nhận đặt thuê HomeStay: http://localhost:4200/confirm/" + bill.getId() + "\n Xin cám ơn đã sử dụng dịch vụ của chúng tôi !!!");
        }
        bill.setStatus(TEXT_WAIT_FOR_CONFIRMATION);
        String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(Calendar.getInstance().getTime());
        bill.setBookingDate(time);
        long startDate = bill.getStartDate().getTime() + oneDay;
        long endDate = bill.getEndDate().getTime() + oneDay;
        Date startDate1 = new Date(startDate);
        Date endDate1 = new Date(endDate);
        bill.setStartDate(startDate1);
        bill.setEndDate(endDate1);
        billService.save(bill);
        if (bill.getService() != null) {
            Service service = serviceService.findByName(bill.getService().toString());
            Set<Service> services = new HashSet<>();
            services.add(service);
            bill.setService(services);
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //List ra các đơn đặt nhà của chủ nhà
    @PostMapping("/listBill")
    public ResponseEntity<Iterable<Bill>> findBillByHouse(@RequestBody House house) {
        Iterable<Bill> bills = billService.findAllByHouseBillOrderByIdAsc(house);
        if (bills == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBill(@PathVariable Long id) {
        Optional<Bill> billOptional = billService.findById(id);
        return billOptional.map(bill -> new ResponseEntity<>(bill, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable Long id, @RequestBody Bill bill) {
        Optional<Bill> billServiceOptional = billService.findById(id);
        return billServiceOptional.map(bill1 -> {
            bill1.setId(bill1.getId());
            bill1.setNameUser(bill.getNameUser());
            bill1.setTelephoneNumber(bill.getTelephoneNumber());
            bill1.setBookingDate(bill.getBookingDate());
            bill1.setStartDate(bill.getStartDate());
            bill1.setEndDate(bill.getEndDate());
            bill1.setComment(bill.getComment());
            bill1.setEvaluate(bill.getEvaluate());
            bill1.setUser(bill.getUser());
            bill1.setHouseBill(bill.getHouseBill());
            bill1.setTotalPrice(bill.getTotalPrice());
            bill1.setStatus(bill.getStatus());
            return new ResponseEntity<>(billService.save(bill1), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/confirm/{id}")
    public ResponseEntity<Bill> confirmBill(@PathVariable Long id, @RequestBody Bill bill) {
        Optional<Bill> billServiceOptional = billService.findById(id);
        return billServiceOptional.map(bill1 -> {
            bill1.setId(bill1.getId());
            bill1.setStatus(TEXT_HOST_CONFIRMETION);
            long startDate = bill.getStartDate().getTime() - oneDay;
            long endDate = bill.getEndDate().getTime() - oneDay;
            for (long i = startDate; i <= endDate; i += oneDay) {
                Date date1 = new Date(i);
                HouseDay houseDay = new HouseDay();
                houseDay.setDate(date1);
                houseDay.setStatus(true);
                houseDay.setHouseDate(bill.getHouseBill());
                houseDayService.save(houseDay);
            }
            return new ResponseEntity<>(billService.save(bill1), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/confirm")
    public ResponseEntity<Bill> confirmBillByHost(@RequestBody Bill bill) {
        Optional<Bill> billServiceOptional = billService.findById(bill.getId());
        return billServiceOptional.map(bill1 -> {
            bill1.setId(bill1.getId());
            bill1.setStatus(TEXT_HIRING);
            if(bill.getEvaluate() != null){
                bill1.setEvaluate(bill.getEvaluate());
            }
            if(bill.getComment() != null){
                bill1.setComment(bill.getComment());
            }
            return new ResponseEntity<>(billService.save(bill1), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/deleteBill")
    public ResponseEntity<Bill> deleteBillService(@RequestBody Bill bill) {
        Optional<Bill> billServiceOptional = billService.findById(bill.getId());
        long startDate = bill.getStartDate().getTime() - oneDay;
        long endDate = bill.getEndDate().getTime() - oneDay;
        Date startDate1 = new Date(startDate);
        Date endDate1 = new Date(endDate);
        Iterable<HouseDay> listHouseDay = houseDayService.findAllByHouseDateAndDateBetween(bill.getHouseBill(), startDate1, endDate1);
        for (HouseDay houseDay : listHouseDay) {
            houseDayService.remove(houseDay.getId());
        }
        return billServiceOptional.map(bill1 -> {
            billService.remove(bill.getId());
            return new ResponseEntity<>(bill1, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/usersTrue/{id}")
    public ResponseEntity<Iterable<Bill>> getAllBillByUserTrue(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(billService.findBillByUser(id, TEXT_HIRING, TEXT_PAID, "haha"),
                HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/usersFalse/{id}")
    public ResponseEntity<Iterable<Bill>> getAllBillByFalse(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(billService.findBillByUser(id, TEXT_WAIT_FOR_CONFIRMATION, TEXT_HOST_CONFIRMETION, TEXT_CANCELLATION),
                HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/deleteList")
    public ResponseEntity deleteListBill(@RequestBody List<Long> id) {
        billService.deleteListBill(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
