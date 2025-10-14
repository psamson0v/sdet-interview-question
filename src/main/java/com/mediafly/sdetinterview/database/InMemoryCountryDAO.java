package com.mediafly.sdetinterview.database;

import com.mediafly.sdetinterview.models.CountryDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryCountryDAO implements CountryDAO {
    private final List<CountryDTO> countries = new ArrayList<>();

    public List<CountryDTO> getCountries() {
        return countries;
    }

    public void addCountry(CountryDTO countryDTO) {
        countries.add(countryDTO);
    }
}
