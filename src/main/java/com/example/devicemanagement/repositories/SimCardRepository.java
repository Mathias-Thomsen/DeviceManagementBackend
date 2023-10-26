package com.example.devicemanagement.repositories;

import com.example.devicemanagement.entities.SimCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SimCardRepository extends JpaRepository<SimCard, String> {

    //Get the max id number for the simCard type to generate the next id.
    @Query("SELECT MAX(sc.id) FROM SimCard sc WHERE sc.id LIKE :simCardTypeAbbreviation%")
    String findMaxSimCardIdForType(@Param("simCardTypeAbbreviation") String simCardTypeAbbreviation);

}
