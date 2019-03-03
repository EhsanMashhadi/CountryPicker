package com.ehsanmashhadi.library.presenter;

import com.ehsanmashhadi.library.repository.ICountryRepository;
import com.ehsanmashhadi.library.view.CountryPicker;
import com.ehsanmashhadi.library.model.Country;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CountryPickerPresenter implements CountryPickerContractor.Presenter {
    private CountryPickerContractor.View mView;
    private ICountryRepository mICountryRepository;
    private List<Country> mCountries;

    public CountryPickerPresenter(ICountryRepository iCountryRepository, CountryPickerContractor.View view) {

        mICountryRepository = iCountryRepository;
        mView = view;
    }

    @Override
    public void getCountries(List<String> exceptCountries) {

        List<Country> countries = mICountryRepository.getCountries();
        if (exceptCountries != null && exceptCountries.size() > 0) {
            countries = exceptCountriesByName(countries, exceptCountries);
        }
        mCountries = countries;
        mView.setCountries(countries);
    }

    private List<Country> exceptCountriesByName(List<Country> countries, List<String> exceptCountriesName) {

        for (String countryName : exceptCountriesName) {
            for (Country country : countries) {
                if (country.getName().toLowerCase().equals(countryName.toLowerCase())) {
                    countries.remove(country);
                    break;
                }
            }
        }
        return countries;
    }

    @Override
    public void filterSearch(String query) {

        List<Country> filteredCountries = new ArrayList<>();
        for (Country country : mCountries) {
            if (country.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredCountries.add(country);
            }
        }
        mView.setCountries(filteredCountries);
    }

    @Override
    public void sort(CountryPicker.Sort sort) {

        switch (sort) {
            case CODE: {
                Comparator<Country> comparator = (country1, country2) -> country1.getCode().compareTo(country2.getCode());
                Collections.sort(mCountries, comparator);
                break;
            }

            case COUNTRY: {
                Comparator<Country> comparator = (country1, country2) -> country1.getName().compareTo(country2.getName());
                Collections.sort(mCountries, comparator);
                break;
            }

            case DIALCODE: {
                Comparator<Country> comparator = (o1, o2) -> o1.getDialCode().compareTo(o2.getDialCode());
                Collections.sort(mCountries, comparator);
                break;
            }
        }
    }
}