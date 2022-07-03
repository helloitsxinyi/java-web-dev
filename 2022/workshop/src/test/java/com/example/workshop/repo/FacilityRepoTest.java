package com.example.workshop.repo;

import com.example.workshop.WorkshopApplication;
import com.example.workshop.domain.Facility;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = WorkshopApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FacilityRepoTest {
    @Autowired
    FacilityRepository frepo;

    @Test
    @Order(1)
    public void createFacilityTest() {
        Facility f = new Facility("Opp HSSML bus stop", "staircase");
        Facility f2 = new Facility("ISS", "Lesson");
        Facility f3 = new Facility("ISS", "tis fake another iss");
        Facility f4 = new Facility("UTown", "katz");
        frepo.save(f);
        frepo.save(f2);
        frepo.save(f3);
        frepo.save(f4);
        Assertions.assertNotNull(f);
        Assertions.assertNotNull(f2);
        Assertions.assertNotNull(f3);
        Assertions.assertNotNull(f4);
    }

    @Test
    @Order(2)
    public void getFacilityTest() {
        Facility f = frepo.findById(2).get();
        Assertions.assertEquals("ISS", f.getName());
    }

    @Test
    @Order(3)
    public void getAllFacilitiesTest() {
        List<Facility> facilityList = frepo.findAll();
        Assertions.assertEquals(4, facilityList.size());
    }

    @Test
    @Order(4)
    public void deleteFacilityTest() {
        frepo.deleteById(1);
        List<Facility> facilityList = frepo.findAll();
        Assertions.assertEquals(3, facilityList.size());
    }

    @Test
    @Order(5)
    public void findFacilitiesByNameTest() {
        List<Facility> facilityList = frepo.findFacilitiesByName("ISS");
        Assertions.assertEquals(2, facilityList.size());
    }
}
