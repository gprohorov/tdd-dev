package edu.pro.tdddev.controller;
/*
  @author   george
  @project   tdd-dev
  @class  PatientController
  @version  1.0.0 
  @since 13.02.23 - 20.24
*/

import edu.pro.tdddev.model.Patient;
import edu.pro.tdddev.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/")
    List<Patient> getAll() {
        return patientService.getAll();
    }

    @GetMapping("/hello")
    String sayHello() {
        return "Hello";
    }


}
