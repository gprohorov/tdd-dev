package edu.pro.tdddev.service;
/*
  @author   george
  @project   tdd-dev
  @class  PatientService
  @version  1.0.0 
  @since 13.02.23 - 20.42
*/

import edu.pro.tdddev.model.Patient;
import edu.pro.tdddev.repository.PatientRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

 //   @PostConstruct
    void init(){
        patientRepository.saveAll(patients);
    }

    public List<Patient> getAll(){
        return patientRepository.findAll();
    }

}
