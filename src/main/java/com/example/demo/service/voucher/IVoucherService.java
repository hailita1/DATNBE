package com.example.demo.service.voucher;

import com.example.demo.model.Voucher;
import com.example.demo.service.IGeneralService;

import java.util.Date;
import java.util.List;

public interface IVoucherService extends IGeneralService<Voucher> {
    void deleteListVoucher(List<Long> model);

    Iterable<Voucher> findByExpiredDateGreaterThanEqual(Date date);
}
