package com.mediafly.sdetinterview.services;

import com.mediafly.sdetinterview.database.CountryDAO;
import com.mediafly.sdetinterview.models.CountryDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

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

    public CountryDTO getCountry(String code) {
        return new CountryDTO(code);
    }
}
