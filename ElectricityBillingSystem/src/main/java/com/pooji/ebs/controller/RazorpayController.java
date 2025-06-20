package com.pooji.ebs.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RazorpayController {

    @Value("${razorpay.key_id}")
    private String razorpayKeyId;

    @Value("${razorpay.key_secret}")
    private String razorpayKeySecret;

    @GetMapping("/test")
    public String testPage() {
        return "test";
    }

    @GetMapping("/pay/bill/{amount}")
    public String openPaymentPage(@PathVariable("amount") double amount, Model model) {
        model.addAttribute("amount", amount);
        model.addAttribute("key", razorpayKeyId);
        return "razorpay-payment";
    }

    @PostMapping("/create_order")
    @ResponseBody
    public String createOrder(@RequestParam("amount") double amount) {
        try {
            RazorpayClient client = new RazorpayClient(razorpayKeyId, razorpayKeySecret);

            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", (int)(amount * 100)); // Convert ₹ to paise
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "txn_123456");

            Order order = client.orders.create(orderRequest);
            return order.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\":\"" + e.getMessage() + "\"}";
        }
    }

}

