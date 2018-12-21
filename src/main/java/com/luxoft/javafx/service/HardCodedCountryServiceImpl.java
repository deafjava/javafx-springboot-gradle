package com.luxoft.javafx.service;

import com.luxoft.javafx.domain.Country;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class HardCodedCountryServiceImpl implements CountryService {

    @Override
    public Set<Country> getAllCountries() {

        Set<Country> result = new HashSet<>();

        result.add(new Country("AU", "Australia"));
        result.add(new Country("BR", "Brazil"));
        result.add(new Country("BE", "Belgium"));

        return result;
    }
}
