package com.worldtravel.demo.repository;

import com.worldtravel.demo.model.Adventure;
import com.worldtravel.demo.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdventureRepository extends JpaRepository<Adventure, Long> {

    Adventure findByCountryId(Long countryId);

    List<Adventure>findByUserId(Long userId);

    List<Adventure>findByUserIdAndCountryId(Long userId, Long countryId);

    Adventure findByUserIdAndCountryName(Long userId, String countryName);

    Adventure findByAdventureNameAndUserId(String adventureName, Long userId);
    Adventure findByUserIdAndId(Long adventureId, Long userId);

}
