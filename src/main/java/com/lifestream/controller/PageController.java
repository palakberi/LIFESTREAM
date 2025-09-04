package com.lifestream.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/admin")
    public String adminPage() {
        return "admin"; // loads templates/admin.html
    }

    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "dashboard"; // loads templates/dashboard.html
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // loads templates/login.html
    }
}
