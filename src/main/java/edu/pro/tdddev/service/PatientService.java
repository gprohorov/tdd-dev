package edu.pro.tdddev.service;
/*
  @author   george
  @project   tdd-dev
  @class  PatientService
  @version  1.0.0 
  @since 13.02.23 - 20.42
*/

import edu.pro.tdddev.model.Patient;
import edu.pro.tdddev.model.PatientRegistrationRequest;
import edu.pro.tdddev.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    private List<Patient> patients = List.of(
            new Patient("1", "John", "012", "Beatles", LocalDateTime.now()),
            new Patient("2", "Paul", "345", "Beatles", LocalDateTime.now()),
            new Patient("3", "Freddie", "678", "Queen", LocalDateTime.now())
    );

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
/*
    @PostConstruct
    void init(){
        patientRepository.deleteAll();
        patientRepository.saveAll(patients);
    }
*/
    public List<Patient> getAll(){
        return patientRepository.findAll();
    }

    public Patient create(PatientRegistrationRequest request) {

        String phone = request.phoneNumber();

        if (patientRepository.existsPatientByPhoneNumber(phone)){
            Patient existingOne = patientRepository.findPatientByPhoneNumber(phone).get();
            if (existingOne.getName().equals(request.name())) {
                throw new IllegalStateException("The patient is already registrated!");
            } else {
                throw  new IllegalStateException(
                        String.format("The number %s is already taken!",phone));
            }
        }

        Patient patientToCreate = new Patient(
                request.name(),
                request.phoneNumber(),
                "",
                LocalDateTime.now());
        return patientRepository.save(patientToCreate);
    }

    public boolean delete(String id) {
        // logic
        patientRepository.deleteById(id);
         return true;
    }

    public Patient get(String id) {
        return  patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    public Patient update(Patient patient) {
        // logic
        return patientRepository.save(patient);
    }

        public Optional<Patient> getByPhone(String phone) {
        // logic
        return patientRepository.findPatientByPhoneNumber(phone);
    }



}

//  CRUD    -  create read update delete
