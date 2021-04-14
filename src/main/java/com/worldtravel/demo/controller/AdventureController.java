package com.worldtravel.demo.controller;

import com.worldtravel.demo.model.Adventure;
import com.worldtravel.demo.model.Country;
import com.worldtravel.demo.repository.AdventureRepository;
import com.worldtravel.demo.service.AdventureService;
import com.worldtravel.demo.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/api")
public class AdventureController {
    private AdventureService adventureService;

    @Autowired
    public void setAdventureService(AdventureService adventureService){
        this.adventureService = adventureService;
    }


    //http://localhost:PORTNUMBER/api/adventures
    @GetMapping("/adventures")
    public List<Adventure> getAdventures (){
        System.out.println("calling getCountries =====>");
        return adventureService.getAdventures();
    }

    //http://localhost:PORTNUMBER/api/adventures/1
    @GetMapping("/adventures/{adventureId}")
    public Adventure getAdventure(@PathVariable Long adventureId){
        System.out.println("calling getAdventure =====>");
        return adventureService.getAdventure(adventureId);
    }

    //http://localhost:PORTNUMBER/api/adventures
    @PostMapping("/adventures")
    public Adventure createAdventure(@RequestBody Adventure adventureObject){
        System.out.println("calling createAdventure =====>");
        return adventureService.createAdventure(adventureObject);
    }

    //http://localhost:PORTNUMBER/api/countries
    @GetMapping("/countries")
    public Set<Country> getCountries(){
        System.out.println("calling getCountries =====>");
        return adventureService.getCountries();
    }

<<<<<<< HEAD
    //http://localhost:PORTNUMBER/api/ADVENTUREID/countries
=======
    //http://localhost:PORTNUMBER/api/adventures/ADVENTUREID/countries
>>>>>>> fb77e84e5bcd02951d89db9be13b1e4adf26d10c
    @GetMapping("/adventures/{adventureId}/countries")
    public Country getCountry(@PathVariable Long adventureId){
        System.out.println("calling getCountry =====>");
        return adventureService.getCountry(adventureId);
    }
    //http://localhost:PORTNUMBER/api/ADVENTUREID
    @DeleteMapping("/adventures/{adventureId}")
    public Adventure deleteAdventure(@PathVariable Long adventureId){
        System.out.println("calling deleteAdventure ======>");
        return adventureService.deleteAdventure(adventureId);
    }

    //http://localhost:PORTNUMBER/api/ADVENTUREID
    @PutMapping("/adventures/{adventureId}")
    public Adventure updateAdventure(@PathVariable Long adventureId, @RequestBody Adventure adventureObject){
        System.out.println("calling updateAdventure =====>");
        return adventureService.updateAdventure(adventureId, adventureObject);
    }
}
