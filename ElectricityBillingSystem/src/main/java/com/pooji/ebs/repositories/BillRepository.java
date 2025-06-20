package com.pooji.ebs.repositories;

import com.pooji.ebs.entities.Bill;
import com.pooji.ebs.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByUser(User user);
}
