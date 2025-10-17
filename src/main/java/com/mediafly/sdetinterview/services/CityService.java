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

    public CityService(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    public List<CityDTO> getCities(String country) {
        if (StringUtils.isEmpty(country)) {
            return cityDAO.getCities();
        } else {
            return cityDAO.getCities(country);
        }
    }

    public void addCity(CityDTO cityDTO) {
        cityDAO.addCity(cityDTO);
    }


    public CityDTO getCity(String name) {
        return cityDAO.getCity(name);
    }

    public List<CityDTO>  getCityByPrefix(String prefix, String country) {
        if (prefix.length() < 2 ) {
            return new ArrayList<>(); // Too few characters for autocomplete
        }
        return cityDAO.getCitiesByPrefix(prefix);
    }
}
