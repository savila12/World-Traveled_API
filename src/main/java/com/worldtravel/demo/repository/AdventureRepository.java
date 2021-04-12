package com.worldtravel.demo.repository;

import com.worldtravel.demo.model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdventureRepository extends JpaRepository<Adventure, Long> {

    List<Adventure> findByCountryId(Long countryId);
    List<Adventure> findByUserId(Long userId);
    Adventure findByAdventureNameAndUserId(String adventureName, Long userId);

}
