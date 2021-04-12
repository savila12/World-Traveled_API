package com.worldtravel.demo.controller;

import com.worldtravel.demo.model.Adventure;
import com.worldtravel.demo.model.Country;
import com.worldtravel.demo.service.AdventureService;
import com.worldtravel.demo.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //http://localhost:PORTNUMBER/api/categories
    @GetMapping("/adventures")
    public List<Adventure> getCountries (){
        System.out.println("calling getCountries =====>");
        return adventureService.getCountries();
    }

    @PostMapping("/adventures")
    public Adventure createAdventure(@RequestBody Adventure adventureObject){
        System.out.println("calling createAdventure =====>");
        return adventureService.createAdventure(adventureObject);
    }
}
