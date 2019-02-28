package com.ehsanmashhadi.countrypicker;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import com.ehsanmashhadi.library.view.CountryPicker;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Locale locale = new Locale("fa");
        Locale.setDefault(locale);
        Configuration config = getBaseContext().getResources().getConfiguration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());


        CountryPicker countryPicker = new CountryPicker.Builder(this).showingDialCode(true).setPreSelectedCountry("guam").showingFlag(true)
                .sortBy(CountryPicker.Sort.NONE).setViewType(CountryPicker.ViewType.BOTTOMSHEET).enablingSearch(true).setListener(country ->
                        Toast.makeText(this, country.getName(), Toast.LENGTH_LONG).show()).enableAutoDetectCountry(CountryPicker.DetectionMethod.LOCALE, country -> {
                    Toast.makeText(this, country.getName(), Toast.LENGTH_LONG).show();
                }).build();
        countryPicker.show(this);


    }

}
