package com.mediafly.sdetinterview.controllers;


import com.mediafly.sdetinterview.services.CountryService;
import org.springframework.web.bind.annotation.*;
import com.mediafly.sdetinterview.models.*;
import java.util.List;

@RestController
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("country/")
    public List<CountryDTO> index() {
        return countryService.getCountries();
    }
    @GetMapping("country/{name}")
    public CountryDTO getCountry(@PathVariable String name) {
        return countryService.getCountry(name);
    }
    @GetMapping("country/search/{name}")
    public CountryDTO getCountryByPrefix(@PathVariable String name) {
        return countryService.getCountry(name);
    }

    @PostMapping("country/")
    public void getCountryByName(@RequestBody CountryDTO country) {
        countryService.addCountry(country);
    }
}
