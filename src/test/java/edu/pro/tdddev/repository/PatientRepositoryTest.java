package edu.pro.tdddev.repository;

import edu.pro.tdddev.model.Patient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/*
  @author   george
  @project   tdd-dev
  @class  PatientRepositoryTest
  @version  1.0.0 
  @since 25.02.23 - 18.01
*/

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataMongoTest
class PatientRepositoryTest {

  @Autowired
  PatientRepository underTest;

    @BeforeEach
    void setUp() {
      Patient john = new Patient("John", "(050)123", "Beatles", LocalDateTime.now().minusDays(5));
      Patient freddie = new Patient("Frederic", "(050)1456", "Queen", LocalDateTime.now().minusDays(7));
      underTest.saveAll(List.of(john, freddie));
    }

    @AfterEach
    void tearDown() {
      underTest.deleteAll();
    }

    @Test
    void itShouldCheckTheCollectionIsNotEmpty(){
      assertFalse(underTest.findAll().isEmpty());
      assertEquals(2, underTest.findAll().size());
    }

    @Test
    void itShouldSavePatient() {
      //given
      Patient paul = new Patient("Paul", "(050)911", "McCartney", LocalDateTime.now().minusDays(3));
      //when
      underTest.save(paul);
      //then
      Patient forTest = underTest.findAll()
              .stream().filter(patient -> patient.getName().equals("Paul"))
              .findFirst()
              .orElse(null);
    System.out.println(forTest.getId());
      assertNotNull(forTest);
      assertNotNull(forTest.getId());
      assertFalse(forTest.getId().isEmpty());
      assertTrue(forTest.getName().equals(paul.getName()));

    }
}
