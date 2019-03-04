package com.ehsanmashhadi.library.presenter;


import com.ehsanmashhadi.library.view.CountryPicker;
import com.ehsanmashhadi.library.model.Country;

import java.util.List;

public interface CountryPickerContractor {

    interface View {

        void setCountries(List<Country> countries);
    }

    interface Presenter {

        void getCountries(List<String> exceptCountries);

        void filterSearch(String query);

        void sort(CountryPicker.Sort sort);
    }
}
