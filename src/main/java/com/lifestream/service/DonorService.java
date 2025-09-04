package com.lifestream.service;

import com.lifestream.model.Appointment;
import com.lifestream.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DonorService {

    private final AppointmentRepository appointmentRepository;

    public DonorService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment scheduleAppointment(String donorId, LocalDateTime time) {
        Appointment appt = new Appointment();
        appt.setDonorId(donorId);
        appt.setAppointmentTime(time);
        appt.setCompleted(false);
        return appointmentRepository.save(appt);
    }

    public List<Appointment> getAppointments(String donorId) {
        return appointmentRepository.findAll()
                .stream()
                .filter(a -> a.getDonorId().equals(donorId))
                .toList();
    }
}
