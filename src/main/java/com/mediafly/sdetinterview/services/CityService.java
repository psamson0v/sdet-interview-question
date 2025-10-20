package com.mediafly.sdetinterview.services;

import com.mediafly.sdetinterview.database.CityDAO;
import com.mediafly.sdetinterview.models.CityDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    private final CityDAO cityDAO;
    private final CountryService countryService;

    public CityService(CityDAO cityDAO, CountryService countryService) {
        this.cityDAO = cityDAO;
        this.countryService = countryService;
    }

    public List<CityDTO> getCities(String country) {
        if (StringUtils.isEmpty(country)) {
            return cityDAO.getCities();
        } else {
            return cityDAO.getCities(country);
        }
    }

    public void addCity(CityDTO cityDTO) {
        if (cityDTO.getName() == null || StringUtils.isEmpty(cityDTO.getName())) {
            throw new IllegalArgumentException("City must have a name");
        }
        if (cityDTO.getName().length() > 100) {
            throw new IllegalArgumentException("City name too long");
        }

        if (countryService.getCountry(cityDTO.getCountry().getName()) != null) {
            cityDAO.addCity(cityDTO);
        }
        else {
            throw new IllegalArgumentException("Cities must have a valid country");
        }
    }


    public CityDTO getCity(String name) {
        return cityDAO.getCity(name);
    }

    public List<CityDTO>  getCityByPrefix(String prefix, String country) {
        if (prefix.length() < 2 ) {
            return new ArrayList<>(); // Too few characters for autocomplete
        }
        List<CityDTO> cities = cityDAO.getCitiesByPrefix(prefix);
        if (country != null) {
            return cities.stream().filter(c -> c.getCountry().getName().equals(country)).toList();
        }
        return cities;
    }
}
