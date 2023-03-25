package edu.pro.tdddev.controller;

import edu.pro.tdddev.model.PatientRegistrationRequest;
import edu.pro.tdddev.service.PatientService;
import edu.pro.tdddev.utils.MyUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
  @author   george
  @project   tdd-dev
  @class  PatientIntegrationTest
  @version  1.0.0 
  @since 19.03.23 - 23.42
*/
@SpringBootTest
@AutoConfigureMockMvc
class PatientIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PatientService patientService;

    @Test
    void itShouldRegisterPatient() throws Exception {
        // given
        String name = "George Harrison";
        String phone = "123321";
        PatientRegistrationRequest request = new PatientRegistrationRequest(name, phone);

        // when
        ResultActions perform = mockMvc.perform(post("http://localhost:8081/api/v1/patients/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(MyUtils.toJson(request)));

        // then
        perform.andExpect(status().isOk());
        assertThat(patientService.getByPhone(phone)).isPresent();
        assertThat(patientService.getByPhone(phone).get().getName().equals(name));
        assertNotNull(patientService.getByPhone(phone).get().getId());
        assertNotNull(patientService.getByPhone(phone).get().getCreatedAt());
    }
    
}
