package com.example.demo.controller;

import com.example.demo.model.RepComment;
import com.example.demo.model.Voucher;
import com.example.demo.service.voucher.IVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Action;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/vouchers")
public class VoucherController {
    @Autowired
    private IVoucherService voucherService;

    @GetMapping
    public ResponseEntity<Iterable<Voucher>> getAllVoucher() {
        return new ResponseEntity<>(voucherService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Voucher> createNewVoucher(@RequestBody Voucher voucher) {
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
            voucher1.setPrice(voucher1.getPrice());
            voucher1.setVoucher_code(voucher1.getVoucher_code());
            voucher1.setTitle(voucher.getTitle());
            voucher1.setStatus(voucher.getStatus());
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
}
