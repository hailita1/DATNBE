package com.example.demo.controller;

import com.example.demo.model.RepComment;
import com.example.demo.service.repComment.RepCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/repcomments")
public class RepCommentController {
    @Autowired
    private RepCommentService repCommentService;

    @GetMapping
    public ResponseEntity<Iterable<RepComment>> getAllRepComment() {
        return new ResponseEntity<>(repCommentService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RepComment> createNewIRepComment(@RequestBody RepComment repComment) {
        return new ResponseEntity<>(repCommentService.save(repComment), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepComment> getRepComment(@PathVariable Long id) {
        Optional<RepComment> repCommentOptional = repCommentService.findById(id);
        return repCommentOptional.map(repComment -> new ResponseEntity<>(repComment, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepComment> updateRepComment(@PathVariable Long id, @RequestBody RepComment repComment) {
        Optional<RepComment> repCommentOptional = repCommentService.findById(id);
        return repCommentOptional.map(repComment1 -> {
            repComment1.setId(repComment1.getId());
            repComment1.setContent(repComment.getContent());
            repComment1.setStatus(repComment.getStatus());
            return new ResponseEntity<>(repCommentService.save(repComment1), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping
    public ResponseEntity<RepComment> deleteRepComment(@RequestBody RepComment repComment) {
        Optional<RepComment> repCommentOptional = repCommentService.findById(repComment.getId());
        return repCommentOptional.map(repComment1 -> {
            repCommentService.remove(repComment.getId());
            return new ResponseEntity<>(repComment1, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
