package com.worldtravel.demo.controller;

import com.worldtravel.demo.model.Adventure;
import com.worldtravel.demo.model.Country;
import com.worldtravel.demo.service.AdventureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    //http://localhost:PORTNUMBER/api/adventures/ADVENTUREID/countries
    @GetMapping("/adventures/{adventureId}/countries")
    public Country getCountry(@PathVariable Long adventureId){
        System.out.println("calling getCountry =====>");
        return adventureService.getCountry(adventureId);
    }

    //http://localhost:PORTNUMBER/api/adventures/ADVENTUREID
    @DeleteMapping("/adventures/{adventureId}")
    public ResponseEntity<HashMap> deleteAdventure(@PathVariable Long adventureId){
        System.out.println("calling deleteAdventure ======>");
        String status = adventureService.deleteAdventure(adventureId);
        HashMap message = new HashMap();
        message.put("message", status);
        return new ResponseEntity<HashMap>(message, HttpStatus.OK);
    }

    //http://localhost:PORTNUMBER/api/adventures/ADVENTUREID
    @PutMapping("/adventures/{adventureId}")
    public Adventure updateAdventure(@PathVariable Long adventureId, @RequestBody Adventure adventureObject){
        System.out.println("calling updateAdventure =====>");
        return adventureService.updateAdventure(adventureId, adventureObject);
    }
}
