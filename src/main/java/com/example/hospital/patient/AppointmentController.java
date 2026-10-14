
package com.example.hospital.patient;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "http://localhost:5174")
public class AppointmentController {

    private final AppointmentRepository repository;

    public AppointmentController(
            AppointmentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Appointment> getAppointmentById(
            @PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping
    public Appointment createAppointment(
            @RequestBody Appointment appointment) {
        return repository.save(appointment);
    }

    @PutMapping("/{id}")
    public Appointment updateAppointment(
            @PathVariable Long id,
            @RequestBody Appointment updatedAppointment) {

        return repository.findById(id)
                .map(appointment -> {
                    appointment.setPatientName(
                            updatedAppointment.getPatientName());
                    appointment.setDoctorName(
                            updatedAppointment.getDoctorName());
                    appointment.setAppointmentDate(
                            updatedAppointment.getAppointmentDate());
                    appointment.setAppointmentTime(
                            updatedAppointment.getAppointmentTime());
                    appointment.setReason(
                            updatedAppointment.getReason());
                    appointment.setStatus(
                            updatedAppointment.getStatus());

                    return repository.save(appointment);
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
