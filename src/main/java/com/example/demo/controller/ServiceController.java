package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.House;
import com.example.demo.model.Service;
import com.example.demo.service.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/services")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @GetMapping
    public ResponseEntity<Iterable<Service>> getAllService() {
        return new ResponseEntity<>(serviceService.findAll(), HttpStatus.OK);
    }

    //List dịch vụ hoạt động
    @PostMapping("/statusTrue")
    public ResponseEntity<Iterable<Service>> getAllCategoryStatusTrue(@RequestBody House house) {
        return new ResponseEntity<>(serviceService.findAllByHouseService(house), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Service> createNewService(@RequestBody Service service) {
        return new ResponseEntity<>(serviceService.save(service), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> getService(@PathVariable Long id) {
        Optional<Service> service = serviceService.findById(id);
        return service.map(service1 -> new ResponseEntity<>(service1, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Service> updateService(@PathVariable Long id, @RequestBody Service service) {
        Optional<Service> serviceOptional = serviceService.findById(id);
        return serviceOptional.map(service1 -> {
            service1.setId(service1.getId());
            service1.setName(service.getName());
            service1.setPrice(service.getPrice());
            service1.setStatus(service.getStatus());
            service1.setTimes(service.getTimes());
            return new ResponseEntity<>(serviceService.save(service1), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/deleteList")
    public ResponseEntity deleteListService(@RequestBody List<Long> id) {
        serviceService.deleteListService(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Service> deleteService(@PathVariable Long id) {
        Optional<Service> serviceOptional = serviceService.findById(id);
        return serviceOptional.map(service1 -> {
            serviceService.remove(id);
            return new ResponseEntity<>(service1, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
