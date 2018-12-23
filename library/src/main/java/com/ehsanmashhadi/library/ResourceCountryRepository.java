package com.ehsanmashhadi.library;

import android.content.res.Resources;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;

class ResourceCountryRepository implements ICountryRepository {

    private Resources mResources;

    ResourceCountryRepository(Resources resources) {

        mResources = resources;
    }

    @Override
    public List<Country> getCountries() {

        List<Country> countries = null;
        InputStream inputStream = mResources.openRawResource(R.raw.countries);
        try {
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            Gson gson = new Gson();
            countries = gson.fromJson(reader, new TypeToken<List<Country>>() {
            }.getType());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return countries;
    }
}
