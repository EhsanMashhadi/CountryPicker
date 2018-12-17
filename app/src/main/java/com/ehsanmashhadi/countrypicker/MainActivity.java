package com.ehsanmashhadi.countrypicker;

import android.os.Bundle;
import android.widget.Toast;

import com.ehsanmashhadi.library.CountryPicker;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CountryPicker countryPicker = new CountryPicker.Builder(this).sortBy(CountryPicker.Sort.NONE).enablingSearch(true).setListener(country ->
                Toast.makeText(this, country.getName(), Toast.LENGTH_LONG).show()).build();
        countryPicker.show(this);


    }
}
