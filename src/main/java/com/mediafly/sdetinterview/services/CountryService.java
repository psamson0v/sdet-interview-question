package com.mediafly.sdetinterview.services;

import com.mediafly.sdetinterview.database.CountryDAO;
import com.mediafly.sdetinterview.models.CountryDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {

    private final CountryDAO countryDAO;

    public CountryService(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }

    public List<CountryDTO> getCountries() {
        return countryDAO.getCountries();
    }

    public void addCountry(CountryDTO countryDTO) {
        countryDAO.addCountry(countryDTO);
    }

    public CountryDTO getCountry(String name) {
        return new CountryDTO(name);
    }

    public List<CountryDTO>  getCountryByPrefix(String prefix) {
        if (prefix.length() < 2 ) {
            return new ArrayList<>(); // Too few characters for autocomplete
        }
        return countryDAO.getCountriesByPrefix(prefix);
    }
}
