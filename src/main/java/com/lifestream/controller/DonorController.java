package com.lifestream.controller;

import com.lifestream.model.Appointment;
import com.lifestream.service.DonorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/donor")
public class DonorController {

    private final DonorService donorService;

    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    @PostMapping("/schedule")
    public ResponseEntity<Appointment> schedule(@RequestParam String donorId,
                                                @RequestParam String time) {
        LocalDateTime parsed = LocalDateTime.parse(time);
        return ResponseEntity.ok(donorService.scheduleAppointment(donorId, parsed));
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> appointments(@RequestParam String donorId) {
        return ResponseEntity.ok(donorService.getAppointments(donorId));
    }
}
