package com.mediafly.sdetinterview.database;

import com.mediafly.sdetinterview.models.CityDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CityDAO {
    void addCity(CityDTO city);
    List<CityDTO> getCities();
    List<CityDTO> getCities(String country);
    List<CityDTO> getCitiesByPrefix(String prefix);

    CityDTO getCity(String name);
}
