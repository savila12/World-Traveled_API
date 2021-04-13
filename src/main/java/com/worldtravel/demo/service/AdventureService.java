package com.worldtravel.demo.service;

import com.worldtravel.demo.exception.InformationExistsException;
import com.worldtravel.demo.exception.InformationNotFoundException;
import com.worldtravel.demo.model.Adventure;
import com.worldtravel.demo.model.Country;
import com.worldtravel.demo.repository.AdventureRepository;
import com.worldtravel.demo.repository.CountryRepository;
import com.worldtravel.demo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdventureService {
    private CountryRepository countryRepository;
    private AdventureRepository adventureRepository;

    @Autowired
    CountryService countryService;

    @Autowired
    public void setCountryRepository(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    @Autowired
    public void setAdventureRepository(AdventureRepository adventureRepository){
        this.adventureRepository = adventureRepository;
    }

    public List<Adventure> getAdventures (){
        System.out.println("service calling getCountries =====>");
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Adventure> adventureList = adventureRepository.findByUserId(myUserDetails.getUser().getId());

        if(adventureList.isEmpty()){
            throw new InformationNotFoundException("no adventures found for that user with id " + myUserDetails.getUser().getId());
        }
        else{
            return adventureList;
        }
    }

    public Adventure createAdventure(Adventure adventureObject) {
        System.out.println("calling createAdventure =====>");
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Country> country = Optional.ofNullable(countryRepository.findByName(adventureObject.getCountryName()));

        if (!country.isPresent()) {
            throw new InformationNotFoundException("Country with provided name " + adventureObject.getCountryName() + " is not found");
        }
        else{
            adventureObject.setUser(myUserDetails.getUser());
            adventureObject.setCountry(adventureObject.getCountry());
            return adventureRepository.save(adventureObject);
        }
    }

    public Set<Country> getCountries(){
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Country> countries = getAdventures().stream().map(adventure -> {
            String countryName = adventure.getCountryName();
            return countryRepository.findByName(countryName);
        }).collect(Collectors.toSet());

        if(countries.isEmpty()){
            throw new InformationNotFoundException("Countries not found");
        }
        else{
            return countries;
        }
    }
}

