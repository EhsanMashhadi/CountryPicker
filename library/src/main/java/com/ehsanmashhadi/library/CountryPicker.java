package com.ehsanmashhadi.library;


import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CountryPicker {

    private Sort mSort;
    private boolean mShowingFlag;
    private boolean mEnablingSearch;
    private Context mContext;
    private List<Country> mCountries;
    private Locale mLocale;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter.OnCountryClickListener mOnCountryClickListener;

    private CountryPicker() {

    }

    public CountryPicker(Builder builder) {

        mShowingFlag = builder.mShowingFlag;
        mSort = builder.mSort;
        mEnablingSearch = builder.mEnablingSearch;
        mOnCountryClickListener = builder.mOnCountryClickListener;
        mLocale = builder.mLocale;
        mContext = builder.mContext;
        initLocale();
        initCountries();
        sort();
    }


    private void initCountries() {

        mCountries = new ArrayList<>();
        InputStream inputStream = mContext.getResources().openRawResource(R.raw.countries);
        try {
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            Gson gson = new Gson();
            mCountries = gson.fromJson(reader, new TypeToken<List<Country>>() {
            }.getType());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void initLocale() {

        if (mLocale != null) {
            Locale.setDefault(mLocale);
            Configuration config = new Configuration();
            config.locale = mLocale;
            config.setLayoutDirection(mLocale);
            mContext.getResources().updateConfiguration(config, mContext.getResources().getDisplayMetrics());
        }
    }

    public void show(AppCompatActivity activity) {

        Dialog dialog = new Dialog(activity);
        View view = activity.getLayoutInflater().inflate(R.layout.layout_countrypicker, null);
        mRecyclerView = view.findViewById(R.id.recyclerview_countries);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(mCountries);

        if (mOnCountryClickListener != null)
            recyclerViewAdapter.setListener(mOnCountryClickListener);

        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setAdapter(recyclerViewAdapter);
        mRecyclerView.setLayoutManager(layoutManager);

        dialog.setContentView(view);
        dialog.show();
    }

    private void sort() {

        switch (mSort) {

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
        }
    }


    public static class Builder {

        private boolean mShowingFlag;
        private boolean mEnablingSearch;
        private Sort mSort = Sort.NONE;
        private RecyclerViewAdapter.OnCountryClickListener mOnCountryClickListener;
        private Context mContext;
        private Locale mLocale;

        public Builder(Context context) {

            mContext = context;
        }

        public Builder sortBy(Sort sort) {

            mSort = sort;
            return this;
        }

        public Builder showingFlag(boolean showingFlag) {

            mShowingFlag = showingFlag;
            return this;
        }

        public Builder enablingSearch(boolean enablingSearch) {

            mEnablingSearch = enablingSearch;
            return this;
        }

        public Builder setListener(RecyclerViewAdapter.OnCountryClickListener onCountryClickListener) {

            mOnCountryClickListener = onCountryClickListener;
            return this;
        }

        public Builder setLocale(Locale locale) {

            mLocale = locale;
            return this;
        }

        public CountryPicker build() {

            return new CountryPicker(this);
        }
    }


    public enum Sort {
        NONE,
        COUNTRY,
        CODE
    }
}
