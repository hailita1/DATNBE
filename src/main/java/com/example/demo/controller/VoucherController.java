package com.example.demo.controller;

import com.example.demo.model.RepComment;
import com.example.demo.model.Voucher;
import com.example.demo.model.auth.User;
import com.example.demo.service.user.IUserService;
import com.example.demo.service.voucher.IVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Action;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/vouchers")
public class VoucherController {
    @Autowired
    private IVoucherService voucherService;

    @Autowired
    private IUserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<Iterable<Voucher>> getAll() {
        Iterable<Voucher> listVoucher = voucherService.findAll();
        long now = new Date().getTime();
        for (Voucher voucher : listVoucher) {
            long sd = voucher.getExpiredDate().getTime();
            if (now > sd) {
                voucher.setStatus(false);
            }
            voucherService.save(voucher);
        }
        return new ResponseEntity<>(listVoucher, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<Voucher>> getAllVoucher() {
        Date now = new Date();
        return new ResponseEntity<>(voucherService.findByExpiredDateGreaterThanEqual(now), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Voucher> createNewVoucher(@RequestBody Voucher voucher) {
        String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(Calendar.getInstance().getTime());
        voucher.setCreate_at(time);
        return new ResponseEntity<>(voucherService.save(voucher), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voucher> getVoucher(@PathVariable Long id) {
        Optional<Voucher> voucherOptional = voucherService.findById(id);
        return voucherOptional.map(voucher -> new ResponseEntity<>(voucher, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voucher> updateVoucher(@PathVariable Long id, @RequestBody Voucher voucher) {
        Optional<Voucher> voucherOptional = voucherService.findById(id);
        return voucherOptional.map(voucher1 -> {
            voucher1.setId(voucher1.getId());
            voucher1.setDiscount(voucher.getDiscount());
            voucher1.setVoucher_code(voucher.getVoucher_code());
            voucher1.setTitle(voucher.getTitle());
            voucher1.setTypeVoucher(voucher.getTypeVoucher());
            return new ResponseEntity<>(voucherService.save(voucher1), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping
    public ResponseEntity<Voucher> deleteVoucher(@RequestBody Voucher voucher) {
        Optional<Voucher> voucherOptional = voucherService.findById(voucher.getId());
        return voucherOptional.map(voucher1 -> {
            voucherService.remove(voucher1.getId());
            return new ResponseEntity<>(voucher1, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/deleteList")
    public ResponseEntity deleteListVoucher(@RequestBody List<Long> id) {
        voucherService.deleteListVoucher(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
