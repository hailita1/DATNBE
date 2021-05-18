package com.example.demo.controller;

import com.example.demo.model.House;
import com.example.demo.model.Image;
import com.example.demo.model.Utilitie;
import com.example.demo.model.auth.User;
import com.example.demo.service.house.IHouseService;
import com.example.demo.service.user.IUserService;
import com.example.demo.service.utilitie.IUtilitieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/utilities")
public class UtilitieController {
    @Autowired
    private IUtilitieService utilitieService;

    @GetMapping
    public ResponseEntity<Iterable<Utilitie>> getAllUtilitie() {
        return new ResponseEntity<>(utilitieService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/statusTrue")
    public ResponseEntity<Iterable<Utilitie>> getAllUtilitieStatusTrue() {
        return new ResponseEntity<>(utilitieService.findAllByStatusTrue(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Utilitie> createNewUtilitie(@RequestBody Utilitie utilitie) {
        return new ResponseEntity<>(utilitieService.save(utilitie), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilitie> getUtilitie(@PathVariable Long id) {
        Optional<Utilitie> utilitieOptional = utilitieService.findById(id);
        return utilitieOptional.map(utilitie -> new ResponseEntity<>(utilitie, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilitie> updateUtilitie(@PathVariable Long id, @RequestBody Utilitie utilitie) {
        Optional<Utilitie> utilitieOptional = utilitieService.findById(id);
        return utilitieOptional.map(utilitie1 -> {
            utilitie1.setId(utilitie1.getId());
            utilitie1.setName(utilitie.getName());
            utilitie1.setDescription(utilitie.getDescription());
            utilitie1.setStatus(utilitie.getStatus());
            return new ResponseEntity<>(utilitieService.save(utilitie1), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/deleteList")
    public ResponseEntity deleteListUtilitie(@RequestBody List<Long> id) {
        utilitieService.deleteListService(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Utilitie> deleteUtilitie(@PathVariable Long id) {
        Optional<Utilitie> utilitieOptional = utilitieService.findById(id);
        return utilitieOptional.map(utilitie1 -> {
            utilitieService.remove(id);
            return new ResponseEntity<>(utilitie1, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
