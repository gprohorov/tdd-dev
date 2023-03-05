package edu.pro.tdddev.service;

import edu.pro.tdddev.model.Patient;
import edu.pro.tdddev.model.PatientRegistrationRequest;
import edu.pro.tdddev.repository.PatientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

/*
  @author   george
  @project   tdd-dev
  @class  PatientServiceTest
  @version  1.0.0 
  @since 05.03.23 - 17.52
*/

class PatientServiceTest {

    @Mock
    private PatientRepository mockRepository;

    private PatientService underTest;

    @Captor
    private ArgumentCaptor<Patient> argumentCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new PatientService(mockRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void itShouldSavePatient() {
        // given
        String phone = "000";
        LocalDateTime time = LocalDateTime.now();
        PatientRegistrationRequest request = new PatientRegistrationRequest("Ivan",phone);
        given(mockRepository.existsPatientByPhoneNumber(phone)).willReturn(false);
        // when
        underTest.create(request);
        //then
        then(mockRepository).should().save(argumentCaptor.capture());
        Patient patientSaved =  argumentCaptor.getValue();
        assertNotNull(patientSaved);
        assertThat(patientSaved.getName()).isEqualTo(request.name());
        assertThat(patientSaved.getPhoneNumber()).isEqualTo(request.phoneNumber());
        verify(mockRepository).save(patientSaved);
        verify(mockRepository, times(1)).save(patientSaved);
    }
    @Test
    void itShouldNotSavePatientWhenPhoneIsInBase() {
        // given
        String phone = "001";
        LocalDateTime time = LocalDateTime.now();
        PatientRegistrationRequest request = new PatientRegistrationRequest("Ivan",phone);
        given(mockRepository.existsPatientByPhoneNumber(phone)).willReturn(true);
        // when
        underTest.create(request);
        //then
        verify(mockRepository, never()).save(any(Patient.class));
    }




}
