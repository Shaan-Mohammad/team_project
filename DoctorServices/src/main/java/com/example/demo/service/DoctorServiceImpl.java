package com.example.demo.service;
package com.hospital.doctor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hospital.doctor.entity.Doctor;
import com.hospital.doctor.exception.ResourceNotFoundException;
import com.hospital.doctor.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository repository;

    public DoctorServiceImpl(DoctorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return repository.save(doctor);
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Doctor not found with id: " + id));
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return repository.findAll();
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor doctor) {

        Doctor existingDoctor = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Doctor not found with id: " + id));

        existingDoctor.setName(doctor.getName());
        existingDoctor.setSpecialization(
                doctor.getSpecialization());
        existingDoctor.setExperience(
                doctor.getExperience());
        existingDoctor.setConsultationFee(
                doctor.getConsultationFee());
        existingDoctor.setPhone(
                doctor.getPhone());
        existingDoctor.setEmail(
                doctor.getEmail());
        existingDoctor.setAvailable(
                doctor.getAvailable());

        return repository.save(existingDoctor);
    }

    @Override
    public void deleteDoctor(Long id) {

        Doctor doctor = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Doctor not found with id: " + id));

        repository.delete(doctor);
    }
}
