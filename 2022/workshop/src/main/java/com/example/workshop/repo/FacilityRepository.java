package com.example.workshop.repo;

import com.example.workshop.domain.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface FacilityRepository extends JpaRepository<Facility, Integer> {

    @Query("select f from Facility f where f.name = :name")
    public ArrayList<Facility> findFacilitiesByName(@Param("name") String facilityName);
}
