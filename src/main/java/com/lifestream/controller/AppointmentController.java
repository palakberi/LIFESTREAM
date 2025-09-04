package com.lifestream.controller;

import com.lifestream.model.Appointment;
import com.lifestream.service.AppointmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // ✅ Schedule a new appointment
    @PostMapping("/schedule")
    public ResponseEntity<Appointment> schedule(
            @RequestParam String donorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time) {
        return ResponseEntity.ok(appointmentService.scheduleAppointment(donorId, time));
    }

    // ✅ Get all appointments
    @GetMapping
    public ResponseEntity<List<Appointment>> getAll() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    // ✅ Get donor's appointments
    @GetMapping("/donor/{donorId}")
    public ResponseEntity<List<Appointment>> getDonorAppointments(@PathVariable String donorId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByDonor(donorId));
    }

    // ✅ Mark appointment as completed
    @PostMapping("/{id}/complete")
    public ResponseEntity<Appointment> complete(@PathVariable String id) {
        return ResponseEntity.ok(appointmentService.completeAppointment(id));
    }
}

