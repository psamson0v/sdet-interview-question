package com.mediafly.sdetinterview.database;

import com.mediafly.sdetinterview.models.CountryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CountryDAO {
    void addCountry(CountryDTO country);
    List<CountryDTO> getCountries();
    List<CountryDTO> getCountriesByPrefix(String prefix);
}
