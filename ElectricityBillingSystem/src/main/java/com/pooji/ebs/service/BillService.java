package com.pooji.ebs.service;

import com.pooji.ebs.entities.Bill;
import com.pooji.ebs.entities.User;
import com.pooji.ebs.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepo;

    public Bill saveBill(Bill bill) {
        return billRepo.save(bill);
    }

    public List<Bill> getBillsByUser(User user) {
        return billRepo.findByUser(user);
    }
    public Bill getBillById(Long id) {
        return billRepo.findById(id).orElse(null);
    }

    public void markBillAsPaid(Long id) {
        Bill bill = getBillById(id);
        if (bill != null && bill.getStatus().equals("UNPAID")) {
            bill.setStatus("PAID"); // ✅ Correct value
            billRepo.save(bill);
        }
    }

}


