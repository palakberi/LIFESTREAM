package com.lifestream.controller;

import com.lifestream.dto.LoginRequest;
import com.lifestream.dto.SignupRequest;
import com.lifestream.model.Appointment;
import com.lifestream.repository.AppointmentRepository;
import com.lifestream.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class ViewController {

    private final AuthService authService;
    private final AppointmentRepository appointmentRepository;

    public ViewController(AuthService authService, AppointmentRepository appointmentRepository) {
        this.authService = authService;
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping("/")
    public String index() {
        return "index"; // loads index.html
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute SignupRequest request, Model model) {
        String msg = authService.signup(request);
        model.addAttribute("error", msg);
        return "index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest request, Model model) {
        // ✅ Admin bypass
        if ("admin".equalsIgnoreCase(request.getUsername()) &&
                "admin123".equals(request.getPassword())) {
            model.addAttribute("username", "admin");
            model.addAttribute("role", "ADMIN");
            return "admin"; // loads admin.html
        }

        // ✅ Normal users
        boolean success = authService.login(request);
        if (success) {
            model.addAttribute("username", request.getUsername());
            String role = authService.getUserRole(request.getUsername());
            model.addAttribute("role", role);
            return "dashboard"; // loads dashboard.html
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "index";
        }
    }

    @PostMapping("/donor/schedule")
    public String scheduleDonation(@RequestParam("appointmentTime") String appointmentTime,
                                   @RequestParam("donorId") String donorId,
                                   Model model) {
        try {
            LocalDateTime time = LocalDateTime.parse(appointmentTime);
            Appointment appointment = new Appointment();
            appointment.setDonorId(donorId);
            appointment.setAppointmentTime(time);
            appointment.setCompleted(false);

            appointmentRepository.save(appointment);
            model.addAttribute("message", "Donation scheduled successfully for " + appointmentTime);
        } catch (Exception e) {
            model.addAttribute("message", "⚠️ Failed to schedule donation: " + e.getMessage());
        }

        model.addAttribute("username", donorId);
        model.addAttribute("role", "DONOR");
        return "dashboard";
    }
}
