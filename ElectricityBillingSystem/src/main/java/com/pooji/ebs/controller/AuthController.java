package com.pooji.ebs.controller;


import com.pooji.ebs.entities.User;
import com.pooji.ebs.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String homeRedirect() {
        return "redirect:/login";
    }


    // Show login page
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Show register page
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User()); // For form binding
        return "register";
    }


    // Handle registration
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        if (userService.getUserByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email already exists!");
            return "register";
        }

        userService.registerUser(user);
        model.addAttribute("success", "Registered successfully! Please login.");
        return "login";
    }

    // Handle login
    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {

        User user = userService.loginUser(email, password);
        if (user == null) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }

        // Set session
        session.setAttribute("user", user);


        if ("ADMIN".equals(user.getRole())) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/user/dashboard";
        }
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }
}
