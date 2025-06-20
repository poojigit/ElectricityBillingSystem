package com.pooji.ebs.controller;

import com.pooji.ebs.entities.Bill;
import com.pooji.ebs.entities.User;
import com.pooji.ebs.service.BillService;
import com.pooji.ebs.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private BillService billService;

    @GetMapping("/admin/dashboard")
    public String adminDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null || !"ADMIN".equals(user.getRole())) {
            return "redirect:/login";
        }

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("adminName", user.getName());
        model.addAttribute("adminEmail", user.getEmail());  // ✅ for filter


        return "admin-dashboard";
    }

    @GetMapping("/admin/bill/add/{userId}")
    public String addBillForm(@PathVariable Long userId, Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("bill", new Bill());
        return "add-bill";
    }

    @PostMapping("/admin/bill/save")
    public String saveBill(@ModelAttribute Bill bill,
                           @RequestParam Long userId) {

        User user = userService.getUserById(userId);
        bill.setUser(user);
        bill.setAmount(bill.getUnits() * 7); // 7 Rs/unit fixed

        billService.saveBill(bill);
        return "redirect:/admin/dashboard";
    }
}
