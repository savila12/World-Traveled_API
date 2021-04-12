package com.worldtravel.demo.controller;

import com.worldtravel.demo.model.Country;
import com.worldtravel.demo.repository.CountryRepository;
import com.worldtravel.demo.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CountryController {

//    private CountryService countryService;
//
//    @Autowired
//    public void setCountryService(CountryService countryService){
//        this.countryService = countryService;
//    }
//
//    @GetMapping("/hello")
//    public String helloWorld(){
//        return "Hello World";
//    }
//
//    //http://localhost:PORTNUMBER/api/categories
//    @GetMapping("/countries")
//    public List<Country> getCountries (){
//        System.out.println("calling getCountries =====>");
//        return countryService.getCountries();
//    }
}
