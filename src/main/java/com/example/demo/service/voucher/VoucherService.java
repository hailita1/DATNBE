package com.example.demo.service.voucher;

import com.example.demo.model.Voucher;
import com.example.demo.repository.IVoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VoucherService implements IVoucherService {
    @Autowired
    private IVoucherRepository voucherRepository;

    @Override
    public Iterable<Voucher> findAll() {
        return voucherRepository.findAll();
    }

    @Override
    public Optional<Voucher> findById(Long id) {
        return voucherRepository.findById(id);
    }

    @Override
    public Voucher save(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

    @Override
    public void remove(Long id) {
        voucherRepository.deleteById(id);
    }

    @Override
    public void deleteListVoucher(List<Long> model) {
        if (model != null) {
            for (int i = 0; i < model.size(); i++) {
                voucherRepository.deleteById(model.get(i));
            }
        }
    }

    @Override
    public Iterable<Voucher> findByExpiredDateGreaterThanEqual(Date date) {
        return voucherRepository.findByExpiredDateGreaterThanEqual(date);
    }
}
