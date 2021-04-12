package com.worldtravel.demo.service;

import com.worldtravel.demo.repository.AdventureRepository;
import com.worldtravel.demo.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    private CountryRepository countryRepository;
    private AdventureRepository adventureRepository;

    @Autowired
    public void setCountryRepository(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }
}
