package com.ehsanmashhadi.countrypicker;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.ehsanmashhadi.library.view.CountryPicker;

import java.util.Arrays;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Switch mSwitchFlags;
    private Switch mSwitchDialCodes;
    private Switch mSwitchSearch;
    private Spinner mSpinnerSort;
    private Spinner mSpinnerViewType;
    private Spinner mSpinnerLocale;
    private Spinner mSpinnerStyle;
    private Spinner mSpinnerDetectionMethod;

    private EditText mEditTextPreselectedCountry;
    private EditText mEditTextExceptCountries;
    private EditText mEditTextWantedCountries;
    CountryPicker countryPicker;

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
        mEditTextPreselectedCountry = findViewById(R.id.edittext_preselectedcountry);
        mSpinnerLocale = findViewById(R.id.spinner_locale);
        mSpinnerStyle = findViewById(R.id.spinner_style);

        mEditTextExceptCountries = findViewById(R.id.edittext_exceptcountries);
        mEditTextWantedCountries = findViewById(R.id.edittext_wantedcountries);
        mSpinnerDetectionMethod = findViewById(R.id.spinner_detectionmethod);
        button.setOnClickListener(v -> display());
    }

    private void display() {
        if (countryPicker == null) {
            countryPicker = new CountryPicker.Builder(this).showingDialCode(mSwitchDialCodes.isChecked())
                    .setLocale(new Locale(mSpinnerLocale.getSelectedItem().toString())).showingFlag(mSwitchFlags.isChecked())
                    .sortBy(CountryPicker.Sort.valueOf(mSpinnerSort.getSelectedItem().toString())).setPreSelectedCountry(mEditTextPreselectedCountry.getText().toString())
                    .setViewType(CountryPicker.ViewType.valueOf(mSpinnerViewType.getSelectedItem().toString())).enablingSearch(mSwitchSearch.isChecked())
                    .setCountries(mEditTextWantedCountries.getText().toString().length() > 0 ? Arrays.asList(mEditTextWantedCountries.getText().toString().split(",")) : null)
                    .exceptCountriesName(Arrays.asList(mEditTextExceptCountries.getText().toString().trim().split(",")))
                    .setCountrySelectionListener(country -> Toast.makeText(this, "Selected Country: " + country.getName(), Toast.LENGTH_LONG).show())
                    .setStyle(getResources().getIdentifier(mSpinnerStyle.getSelectedItem().toString(), "style", getPackageName()))
                    .enableAutoDetectCountry(CountryPicker.DetectionMethod.valueOf(mSpinnerDetectionMethod.getSelectedItem().toString())
                            , country -> Toast.makeText(this, "Detected Country: " + country.getName(), Toast.LENGTH_LONG).show())
                    .build();
        }
        countryPicker.show(this);
    }
}