package com.mediafly.sdetinterview.models;

public class CityDTO {

    private final String name;
    public String getName() {
        return name;
    }

    private final CountryDTO country;
    public CountryDTO getCountry() { return country; }

    public CityDTO(String name, CountryDTO country) {
        this.name = name;
        this.country = country;
    }

}
