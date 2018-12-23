package com.ehsanmashhadi.countrypicker;

import android.os.Bundle;
import android.widget.Toast;

import com.ehsanmashhadi.library.CountryPicker;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> list = new ArrayList<>();
        list.add("afghanistan");
        list.add("ALBANIA");
        list.add("ALgERia");

        CountryPicker countryPicker = new CountryPicker.Builder(this).setStyle(R.style.CountryPickerLightStyle).exceptCountriesName(list)
                .sortBy(CountryPicker.Sort.NONE).setViewType(CountryPicker.ViewType.BOTTOMSHEET).enablingSearch(true).setListener(country ->
                        Toast.makeText(this, country.getName(), Toast.LENGTH_LONG).show()).build();
        countryPicker.show(this);

    }

}
