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

@Service
public class AdventureService {
    private CountryRepository countryRepository;
    private AdventureRepository adventureRepository;

    @Autowired
    public void setCountryRepository(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    @Autowired
    public void setAdventureRepository(AdventureRepository adventureRepository){
        this.adventureRepository = adventureRepository;
    }

    public List<Adventure> getCountries (){
        System.out.println("service calling getCountries =====>");
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Adventure> adventureList = adventureRepository.findByUserId(myUserDetails.getUser().getId());
        //List<Country> countryList = (List<Country>) adventureRepository.findByUserId(myUserDetails.getUser().getId())
                //.stream().filter(country -> country.getCountry() != null).map(Adventure::getCountry);

        if(adventureList.isEmpty()){
            throw new InformationNotFoundException("no adventures found for that user with id " + myUserDetails.getUser().getId());
        }
        else{
            return adventureList;
        }
    }

    public Adventure createAdventure(Adventure adventureObject){
        System.out.println("calling createAdventure =====>");
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Adventure adventure = (Adventure) adventureRepository.findByUserIdAndCountryName(myUserDetails.getUser().getId(), adventureObject.getCountryName());

        if(adventure != null){
            throw new InformationExistsException("adventure with user id " + myUserDetails.getUser().getId() + " already exists");
        }
        if(countryRepository.findByName(adventureObject.getCountryName()) != null){

        }
        else {
            throw new InformationNotFoundException("country name does not exists");
        }
    }
}
