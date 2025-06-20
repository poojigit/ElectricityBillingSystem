package com.pooji.ebs.controller;

import com.pooji.ebs.entities.Bill;
import com.pooji.ebs.entities.User;
import com.pooji.ebs.service.BillService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private BillService billService;

    @GetMapping("/user/dashboard")
    public String userDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null || !"USER".equals(user.getRole())) {
            return "redirect:/login";
        }

        List<Bill> bills = billService.getBillsByUser(user);
        model.addAttribute("bills", bills);
        model.addAttribute("username", user.getName());

        return "user-dashboard";
    }
    @GetMapping("/user/pay/{id}")
    public String payBill(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null || !"USER".equals(user.getRole())) {
            return "redirect:/login";
        }

        billService.markBillAsPaid(id);
        return "redirect:/user/dashboard";
    }

}
