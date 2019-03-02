package com.ehsanmashhadi.countrypicker;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.ehsanmashhadi.library.view.CountryPicker;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Switch mSwitchFlags;
    private Switch mSwitchDialCodes;
    private Switch mSwitchSearch;
    private Spinner mSpinnerSort;
    private Spinner mSpinnerViewType;
    private Spinner mSpinnerLocale;

    private Spinner mSpinnerPreselectedCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button_display);
        mSwitchFlags = findViewById(R.id.switch_flags);
        mSwitchDialCodes = findViewById(R.id.switch_dialcodes);
        mSwitchSearch = findViewById(R.id.switch_search);
        mSpinnerSort = findViewById(R.id.spinner_sort);
        mSpinnerViewType = findViewById(R.id.spinner_viewtype);
        mSpinnerPreselectedCountry = findViewById(R.id.spinner_preselectedcountry);
        mSpinnerLocale = findViewById(R.id.spinner_locale);
        button.setOnClickListener(v -> {
            display();
        });

    }

    private void display() {

        CountryPicker countryPicker = new CountryPicker.Builder(this).showingDialCode(mSwitchDialCodes.isChecked()).setLocale(new Locale(mSpinnerLocale.getSelectedItem().toString()))
                .showingFlag(mSwitchFlags.isChecked()).sortBy(CountryPicker.Sort.valueOf(mSpinnerSort.getSelectedItem().toString()))
                .setViewType(CountryPicker.ViewType.valueOf(mSpinnerViewType.getSelectedItem().toString())).enablingSearch(mSwitchSearch.isChecked())
                .setCountrySelectionListener(country -> Toast.makeText(this, country.getName(), Toast.LENGTH_LONG).show())
                .build();
        countryPicker.show(this);
    }
}
