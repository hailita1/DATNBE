package com.example.demo.controller;

import com.example.demo.model.Image;
import com.example.demo.model.UsageBill;
import com.example.demo.service.usageBill.IUsageBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/usageBill")
public class UsageBillController {
    @Autowired
    private IUsageBillService usageBillService;

    @GetMapping
    public ResponseEntity<Iterable<UsageBill>> getAllUsageBill() {
        return new ResponseEntity<>(usageBillService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsageBill> createNewUsageBill(@RequestBody UsageBill usageBill) {
        return new ResponseEntity<>(usageBillService.save(usageBill), HttpStatus.OK);
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
}
