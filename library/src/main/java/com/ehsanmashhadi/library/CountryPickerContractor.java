package com.ehsanmashhadi.library;


import java.util.List;

interface CountryPickerContractor {

    interface View {

        void setCountries(List<Country> countries);
    }

    interface Presenter {

        void getCountries(List<String> exceptCountries);

        void filterSearch(String query);

        void sort(CountryPicker.Sort sort);
    }
}
