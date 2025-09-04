package com.lifestream.service;

import com.lifestream.model.Appointment;
import com.lifestream.repository.AppointmentRepository;
import com.lifestream.repository.DonorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DonorRepository donorRepository;
    private final InventoryService inventoryService;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              DonorRepository donorRepository,
                              InventoryService inventoryService) {
        this.appointmentRepository = appointmentRepository;
        this.donorRepository = donorRepository;
        this.inventoryService = inventoryService;
    }

    public Appointment scheduleAppointment(String donorId, LocalDateTime time) {
        Appointment appointment = new Appointment();
        appointment.setDonorId(donorId);
        appointment.setAppointmentTime(time);
        appointment.setCompleted(false);
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByDonor(String donorId) {
        return appointmentRepository.findByDonorId(donorId);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // ✅ Mark appointment as completed + update inventory
    public Appointment completeAppointment(String id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        if (!appointment.isCompleted()) {
            appointment.setCompleted(true);

            // find donor to know their blood type
            donorRepository.findByUsername(appointment.getDonorId()).ifPresent(donor -> {
                String bloodType = donor.getBloodType();
                // increase stock by 1 unit
                inventoryService.adjustStock(bloodType, 1);
            });

            appointmentRepository.save(appointment);
        }

        return appointment;
    }
}
