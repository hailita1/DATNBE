package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.model.auth.User;

import com.example.demo.service.house.IHouseService;
import com.example.demo.service.image.ImageService;
import com.example.demo.service.service.IServiceService;
import com.example.demo.service.utilitie.IUtilitieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/houses")
public class HouseController {
    @Autowired
    private IHouseService houseService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private IUtilitieService utilitieService;

    @Autowired
    private IServiceService serviceService;

    //List nhà bên admin
    @GetMapping
    public ResponseEntity<Iterable<House>> getAllHouse() {
        return new ResponseEntity<>(houseService.findByOrderByIdDesc(), HttpStatus.OK);
    }

    //List nhà chưa thuê và sắp xếp theo thời gian
    @GetMapping("/statusTrue")
    public ResponseEntity<Iterable<House>> getAllByStatusTrue() {
        return new ResponseEntity<>(houseService.findByStatusTrueOrderByIdDesc(), HttpStatus.OK);
    }

    //List nhà có số đặt thuê giảm dần
    @GetMapping("/numberHires")
    public ResponseEntity<Iterable<House>> findByStatusTrueOrderByNumberHiresDesc() {
        return new ResponseEntity<>(houseService.findByStatusTrueOrderByNumberHiresDesc(), HttpStatus.OK);
    }

    //List nhà giảm giá thuê giảm dần
    @GetMapping("/discount")
    public ResponseEntity<Iterable<House>> findByStatusTrueAndDiscountGreaterThanOrderByDiscountDesc() {
        return new ResponseEntity<>(houseService.findByStatusTrueAndDiscountGreaterThanOrderByDiscountDesc(Long.parseLong("0")), HttpStatus.OK);
    }

    //List nhà giảm giá thuê giảm dần
    @GetMapping("/priceDesc")
    public ResponseEntity<Iterable<House>> findByStatusTrueOrderByPriceDesc() {
        return new ResponseEntity<>(houseService.findByStatusTrueOrderByPriceDesc(), HttpStatus.OK);
    }

    //List nhà giảm giá thuê giảm dần
    @GetMapping("/priceAsc")
    public ResponseEntity<Iterable<House>> findByStatusTrueOrderByPriceAsc() {
        return new ResponseEntity<>(houseService.findByStatusTrueOrderByPriceAsc(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createHouse(@RequestBody House house) {
        house.setUtilitie(house.getUtilitie());
        houseService.save(house);
        if (house.getImages() != null) {
            for (Image image : house.getImages()) {
                image.setHouseImage(house);
                imageService.save(image);
            }
        }
        if (house.getServices() != null) {
            for (Service service : house.getServices()) {
                service.setHouseService(house);
                serviceService.save(service);
            }
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<House> getHouse(@PathVariable Long id) {
        Optional<House> houseOptional = houseService.findById(id);
        return houseOptional.map(house -> new ResponseEntity<>(house, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<House> updateHouse(@PathVariable Long id, @RequestBody House house) {
        Optional<House> houseOptional = houseService.findById(house.getId());
        return houseOptional.map(house1 -> {
            house1.setId(house1.getId());
            house1.setName(house.getName());
            house1.setAddress(house.getAddress());
            house1.setDescription(house.getDescription());
            house1.setNumberRoom(house.getNumberRoom());
            house1.setCategory(house.getCategory());
            house1.setPrice(house.getPrice());
            house1.setAcreage(house.getAcreage());
            house1.setDiscount(house.getDiscount());
            house1.setStatus(house.getStatus());
            Iterable<Image> images = imageService.findAllByHouseImage(house);
            for (Image image : images) {
                imageService.remove(image.getId());
            }
            if (house.getImages() != null) {
                for (Image image : house.getImages()) {
                    image.setHouseImage(house);
                    imageService.save(image);
                }
            }
            Iterable<Service> services = serviceService.findAllByHouseService(house);
            for (Service service : services) {
                serviceService.remove(service.getId());
            }
            if (house.getServices() != null) {
                for (Service service : house.getServices()) {
                    service.setHouseService(house);
                    serviceService.save(service);
                }
            }
            house1.setUtilitie(house.getUtilitie());
            return new ResponseEntity<>(houseService.save(house1), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/updateNumberHires")
    public ResponseEntity<House> updateNumberHires(@RequestBody House house) {
        Optional<House> houseOptional = houseService.findById(house.getId());
        return houseOptional.map(house1 -> {
            house1.setId(house1.getId());
            house1.setNumberHires(house.getNumberHires());
            return new ResponseEntity<>(houseService.save(house1), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<House> deleteHouse(@PathVariable Long id) {
        Optional<House> houseOptional = houseService.findById(id);
        return houseOptional.map(house1 -> {
            houseService.remove(id);
            return new ResponseEntity<>(house1, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/deleteList")
    public ResponseEntity deleteListHouse(@RequestBody List<Long> id) {
        houseService.deleteListService(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //List nhà của chủ nhà
    @PostMapping("/listHouse")
    public ResponseEntity<Iterable<House>> findAllByUser(@RequestBody User user) {
        Iterable<House> houses = houseService.findAllByUser(user);
        if (houses == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(houses, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<House>> getAllProductByName(@RequestParam(name = "address") String address) {
        return new ResponseEntity<>(houseService.findAllByAddressContaining(address), HttpStatus.OK);
    }

    //Tìm kiếm nhà theo nhiều tiêu chi
    @GetMapping("/searchAdvanced")
    public ResponseEntity<Iterable<House>> searchAdvanced(@RequestParam(name = "address") String address,
                                                          @RequestParam(name = "numberRoom") String numberRoom,
                                                          @RequestParam(name = "upperBound") String upperBound,
                                                          @RequestParam(name = "lowerBound") String lowerBound) {
        Iterable<House> houses = null;
        if (Long.parseLong(numberRoom) == 0) {
            if (Double.parseDouble(upperBound) != 0) {
                houses = houseService.findAllByAddressContainsAndPriceBetweenAndStatusTrue(address, Double.parseDouble(lowerBound), Double.parseDouble(upperBound));
            } else {
                houses = houseService.findAllByAddressContaining(address);
            }
        } else {
            if (Double.parseDouble(upperBound) != 0) {
                houses = houseService.findAllByAddressContainsAndNumberRoomLessThanEqualAndPriceBetweenAndStatusTrue(address, Long.parseLong(numberRoom), Double.parseDouble(lowerBound), Double.parseDouble(upperBound));
            } else {
                houses = houseService.findAllByAddressContainsAndNumberRoomLessThanEqualAndStatusTrueOrPriceBetween(address, Long.parseLong(numberRoom), Double.parseDouble(lowerBound), Double.parseDouble(upperBound));
            }
        }
        if (houses == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(houses, HttpStatus.OK);
    }
}
