package com.worldtravel.demo.service;

import com.worldtravel.demo.exception.InformationNotFoundException;
import com.worldtravel.demo.model.Country;
import com.worldtravel.demo.repository.AdventureRepository;
import com.worldtravel.demo.repository.CountryRepository;
import com.worldtravel.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private CountryRepository countryRepository;
    private AdventureRepository adventureRepository;

    @Autowired
    public void setCountryRepository(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    public List<Country> getCountries (){
        System.out.println("calling getCountries =====>");
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Country> countryList = countryRepository.findByUserId(myUserDetails.getUser().getId());

        if(countryList.isEmpty()){
            throw new InformationNotFoundException("no countries found for that user with id " + myUserDetails.getUser().getId());
        }
        else{
            return countryList;
        }
    }
}
