package com.MTube.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.MTube.entity.User;
import com.MTube.services.userServices;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

@Controller
public class paymentController {

    @Autowired
    userServices service;

    // ✅ Create Razorpay Order
    @PostMapping("/createOrder")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> createOrder() {
        Map<String, Object> response = new HashMap<>();
        try {
            RazorpayClient razorpay = new RazorpayClient("rzp_test_A9p6rhUUEHx94W",
                    "ujE59291HJ9ZYb0RjeONCJoD");

            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", 10000); // amount in paise (₹100)
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "receipt#1");

            Order order = razorpay.orders.create(orderRequest);

            response.put("id", order.get("id"));
            response.put("amount", order.get("amount"));
            response.put("currency", order.get("currency"));
            response.put("status", order.get("status"));

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("error", "Exception while creating order");
            return ResponseEntity.badRequest().body(response);
        }
    }

    // ✅ Verify Payment Signature
    @PostMapping("/verifyPayment")
    @ResponseBody
    public boolean verifyPayment(@RequestBody Map<String, String> data) {
        try {
            String orderId = data.get("orderId");
            String paymentId = data.get("paymentId");
            String signature = data.get("signature");

            String verificationData = orderId + "|" + paymentId;

            return Utils.verifySignature(verificationData, signature, "ujE59291HJ9ZYb0RjeONCJoD");

        } catch (RazorpayException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ On success - mark user as premium
    @GetMapping("/payment-success")
    public String paymentSuccess(HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            User user = service.getUser(email);
            if (user != null) {
                user.setPremium(true);
                service.updateUser(user);
            }
        }
        return "login"; // after success redirect to login
    }

    // ✅ On failure - redirect
    @GetMapping("/payment-failure")
    public String paymentFailure() {
        return "login";
    }
}
