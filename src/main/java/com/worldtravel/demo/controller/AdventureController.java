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

    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World";
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

    //http://localhost:PORTNUMBER/api/ADVENTUREID/countries/COUNTRYID
    @GetMapping("/adventures/{adventureId}/countries/{countryId}")
    public Country getCountry(@PathVariable Long adventureId, @PathVariable Long countryId){
        System.out.println("calling getCountry =====>");
        return adventureService.getCountry(adventureId, countryId);
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
