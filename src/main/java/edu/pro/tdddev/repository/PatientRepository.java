package edu.pro.tdddev.repository;

import edu.pro.tdddev.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/*
  @author   george
  @project   tdd-dev
  @class  PatientRepository
  @version  1.0.0 
  @since 13.02.23 - 20.16
*/
@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
    boolean existsPatientByPhoneNumber(String phone);
    Optional<Patient> findPatientByPhoneNumber(String phone);
}
