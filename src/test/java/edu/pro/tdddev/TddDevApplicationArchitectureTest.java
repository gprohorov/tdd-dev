package edu.pro.tdddev;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static org.junit.jupiter.api.Assertions.*;

/*
  @author   george
  @project   tdd-dev
  @class  TddDevApplicationArchitectureTest
  @version  1.0.0 
  @since 17.02.23 - 18.39
*/

class TddDevApplicationArchitectureTest {

    private JavaClasses importedClasses;

    @BeforeEach
    void setUp() {
        importedClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_JARS)
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_ARCHIVES)
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_PACKAGE_INFOS)
                .importPackages("edu.pro.tdddev");
    }

    @Test
    void shouldFollowLayersArchitecture(){
        layeredArchitecture()
                .layer("Controller").definedBy("..controller..")
                .layer("Service").definedBy("..service..")
                .layer("Repository").definedBy("..repository..")
                //
                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller","Service")
                .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service")
               //
                .check(importedClasses);
    };

    @Test
    void servicesShouldNotDependOnControllerLevel(){

        noClasses()
                .that().resideInAPackage("..service..")
                .should()
                .dependOnClassesThat()
                .resideInAPackage("..controller..")
                .because(" out of rules")
                .check(importedClasses);
    }

    @Test
    void controllerClassesShouldBeNamedXController(){
        classes()
                .that().resideInAPackage("..controller..")
                .should()
                .haveSimpleNameEndingWith("Controller")
                .check(importedClasses);

    }
    // TODO: check the names of services and repositories

    @Test
    void cotrollerClassesShouldBeAnnotatedByRestController(){
        classes()
                .that().resideInAPackage("..controller..")
                .should()
                .beAnnotatedWith(RestController.class)
                .andShould()
                .beAnnotatedWith(RequestMapping.class)
                .check(importedClasses);
    }

    @Test
    void shouldNotUseFieldsAutowired(){
        noFields()
                .should().beAnnotatedWith(Autowired.class)
                .check(importedClasses);

    }


}
