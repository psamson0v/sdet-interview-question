package com.mediafly.sdetinterview.controllers;


import com.mediafly.sdetinterview.services.CityService;
import org.springframework.web.bind.annotation.*;
import com.mediafly.sdetinterview.models.*;
import java.util.List;

@RestController
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("city/")
    public List<CityDTO> index(@RequestParam("country") String country) {
        return cityService.getCities(country);
    }
    @GetMapping("city/{name}")
    public CityDTO getCity(@PathVariable String name) {
        return cityService.getCity(name);
    }
    @GetMapping("city/search/{name}")
    public List<CityDTO> getCityByPrefix(@PathVariable String name, @RequestParam("country") String country) {
        return cityService.getCityByPrefix(name, country);
    }

    @PostMapping("city/")
    public void getCityByName(@RequestBody CityDTO city) {
        cityService.addCity(city);
    }
}
