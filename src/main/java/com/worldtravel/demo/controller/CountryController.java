package com.worldtravel.demo.controller;

import com.worldtravel.demo.repository.CountryRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class CountryController {
    private CountryRepository countryRepository;

    private CountryService countryService;
}
