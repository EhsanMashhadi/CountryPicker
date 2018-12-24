package com.ehsanmashhadi.library.repository;

import com.ehsanmashhadi.library.model.Country;

import java.util.List;

public interface ICountryRepository {

    List<Country> getCountries();
}
