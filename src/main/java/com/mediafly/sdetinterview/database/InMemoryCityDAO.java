package com.mediafly.sdetinterview.database;

import com.mediafly.sdetinterview.models.CityDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryCityDAO implements CityDAO {
    private final List<CityDTO> cities = new ArrayList<>();

    public List<CityDTO> getCities() {
        return cities;
    }

    @Override
    public List<CityDTO> getCities(String country) {
        return cities.stream().filter(c -> c.getCountry().getName().equals(country)).toList();
    }

    @Override
    public List<CityDTO> getCitiesByPrefix(String prefix) {
        return cities.stream().filter(c -> c.getName().startsWith(prefix)).toList();
    }

    @Override
    public CityDTO getCity(String name) {
        return cities.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }

    public void addCity(CityDTO cityDTO) {
        cities.add(cityDTO);
    }
}
