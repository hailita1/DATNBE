package com.example.demo.service.usageBill;

import com.example.demo.model.UsageBill;
import com.example.demo.repository.IUsageBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsageBillService implements IUsageBillService {

    @Autowired
    private IUsageBillRepository usageBillRepository;

    @Override
    public Iterable<UsageBill> findAll() {
        return usageBillRepository.findAll();
    }

    @Override
    public Optional<UsageBill> findById(Long id) {
        return usageBillRepository.findById(id);
    }

    @Override
    public UsageBill save(UsageBill usageBill) {
        return usageBillRepository.save(usageBill);
    }

    @Override
    public void remove(Long id) {
        usageBillRepository.deleteById(id);
    }
}
