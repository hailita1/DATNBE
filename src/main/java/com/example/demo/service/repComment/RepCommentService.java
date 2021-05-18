package com.example.demo.service.repComment;

import com.example.demo.model.RepComment;
import com.example.demo.repository.IRepCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RepCommentService implements IRepCommentService {
    @Autowired
    private IRepCommentRepository repCommentRepository;

    @Override
    public Iterable<RepComment> findAll() {
        return repCommentRepository.findAll();
    }

    @Override
    public Optional<RepComment> findById(Long id) {
        return repCommentRepository.findById(id);
    }

    @Override
    public RepComment save(RepComment repComment) {
        return repCommentRepository.save(repComment);
    }

    @Override
    public void remove(Long id) {
        repCommentRepository.deleteById(id);
    }
}
