package com.worldtravel.demo.repository;

import com.worldtravel.demo.model.Adventure;
import com.worldtravel.demo.model.AdventureKey;
import com.worldtravel.demo.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Country findByName(String CountryName);
    Optional<Country> findById(Long id);
   // List<Country> findByUserId(Long userId);
}
