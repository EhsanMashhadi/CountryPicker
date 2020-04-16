package com.ehsanmashhadi.library.view;


import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;

import com.ehsanmashhadi.library.R;
import com.ehsanmashhadi.library.model.Country;
import com.ehsanmashhadi.library.presenter.CountryPickerContractor;
import com.ehsanmashhadi.library.presenter.CountryPickerPresenter;
import com.ehsanmashhadi.library.repository.ResourceCountryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CountryPicker implements CountryPickerContractor.View {

    public interface OnAutoDetectCountryListener {
        void onCountryDetected(Country country);
    }

    private Sort mSort;
    private ViewType mViewType;
    private boolean mShowingFlag;
    private boolean mEnablingSearch;
    private boolean mShowingDialCode;
    private String mPreSelectedCountry;

    private Context mContext;
    private List<Country> mCountries;
    private List<String> mCountriesName;
    private List<String> mExceptCountriesName;
    private Locale mLocale;
    private RecyclerView mRecyclerView;
    private SearchView mSearchViewCountry;
    private int mStyle;
    private DetectionMethod mDetectionMethod;
    private BaseView mBaseView;
    private RecyclerViewAdapter.OnCountryClickListener mOnCountryClickListener;
    private OnAutoDetectCountryListener mOnAutoDetectCountryListener;
    private View mView;
    private CountryPickerContractor.Presenter mPresenter;

    private CountryPicker() {

    }

    private CountryPicker(Builder builder) {

        initAttributes(builder);
        mPresenter = new CountryPickerPresenter(new ResourceCountryRepository(builder.mContext.getResources()), this);
        initLocale();
        initView();
        initCountries();
        sort();
        setPreselectedCountry();
        initSearchView();
        initDetectionMethod();
    }

    private void sort() {

        mPresenter.sort(mSort);
    }

    private void setPreselectedCountry() {

        ((RecyclerViewAdapter) mRecyclerView.getAdapter()).setCountries(mCountries);
        if (mPreSelectedCountry != null) {
            for (Country country : mCountries) {
                if (country.getName().toLowerCase().equals(mPreSelectedCountry.toLowerCase())) {
                    mRecyclerView.scrollToPosition(mCountries.indexOf(country));
                    break;
                }
            }
        }
    }

    private void initAttributes(Builder builder) {

        mCountriesName = builder.mCountries;
        mOnAutoDetectCountryListener = builder.mOnAutoDetectCountryListener;
        mDetectionMethod = builder.mDetectionMethod;
        mShowingFlag = builder.mShowingFlag;
        mSort = builder.mSort;
        mViewType = builder.mViewType;
        mEnablingSearch = builder.mEnablingSearch;
        mOnCountryClickListener = builder.mOnCountryClickListener;
        mShowingDialCode = builder.mShowingDialCode;
        mLocale = builder.mLocale;
        mContext = builder.mContext;
        mExceptCountriesName = builder.mExceptCountries;
        mStyle = builder.mStyle;
        mPreSelectedCountry = builder.mPreSelectedCountry;
        if (mStyle == 0) {
            mStyle = R.style.CountryPickerLightStyle;
        }
        builder.mContext.getTheme().applyStyle(mStyle, true);
    }

    private void initView() {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        mView = layoutInflater.inflate(R.layout.layout_countrypicker, null);
        mRecyclerView = mView.findViewById(R.id.recyclerview_countries);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(mCountries, mPreSelectedCountry, mShowingFlag, mShowingDialCode);
        if (mOnCountryClickListener != null)
            recyclerViewAdapter.setListener(country -> {
                mOnCountryClickListener.onCountrySelected(country);
                dismiss();
            });
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(recyclerViewAdapter);
        mRecyclerView.setLayoutManager(layoutManager);

    }

    public List<Country> getCountries() {

        return mCountries;
    }

    private void initSearchView() {

        mSearchViewCountry = mView.findViewById(R.id.searchview_country);
        mSearchViewCountry.setOnClickListener(v -> mSearchViewCountry.setIconified(false));

        if (mEnablingSearch) {
            mSearchViewCountry.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    mPresenter.filterSearch(newText);
                    return true;
                }
            });
        } else {
            mSearchViewCountry.setVisibility(View.GONE);
        }
    }

    private void initCountries() {

        mPresenter.getCountries(mExceptCountriesName);
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

    private void initDetectionMethod() {

        switch (mDetectionMethod) {
            case LOCALE:
                detectByLocale();
                break;
            case SIM:
                detectBySim();
                break;
            case NETWORK:
                detectByNetwork();
                break;
        }
    }

    private void detectByLocale() {

        Locale locale;
        String countryValue;

        if (mLocale != null)
            countryValue = mLocale.getCountry();
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                locale = mContext.getResources().getConfiguration().getLocales().get(0);
            } else {
                locale = mContext.getResources().getConfiguration().locale;
            }
            countryValue = locale.getCountry();
        }
        Country country = getCountryByCode(countryValue);
        mOnAutoDetectCountryListener.onCountryDetected(country);
    }

    private Country getCountryByCode(String countryIso) {

        if (countryIso == null || countryIso.equals(""))
            countryIso = "us";

        for (Country country : mCountries) {
            if (country.getCode().toLowerCase().equals(countryIso.toLowerCase())) {
                return country;
            }
        }
        return mCountries.get(0);
    }


    private void detectBySim() {

        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        String countryCode = tm.getSimCountryIso();
        Country country = getCountryByCode(countryCode);
        mOnAutoDetectCountryListener.onCountryDetected(country);
    }

    private void detectByNetwork() {

        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        String countryCode = tm.getNetworkCountryIso();
        Country country = getCountryByCode(countryCode);
        mOnAutoDetectCountryListener.onCountryDetected(country);

    }

    public void show(AppCompatActivity activity) {

        if (mBaseView == null) {
            mBaseView = ViewFactory.create(mViewType, activity);
            mBaseView.setView(mView);
        }
        mBaseView.showView();
    }

    public void dismiss() {

        mBaseView.dismissView();
    }

    @Override
    public void setCountries(List<Country> countries) {

        if (mCountriesName != null) {
            mCountries = new ArrayList<>();
            for (String countryName : mCountriesName) {
                for (Country country : countries) {
                    if (countryName.toLowerCase().equals(country.getName().toLowerCase())) {
                        mCountries.add(country);
                        break;
                    }
                }
            }
        } else {
            mCountries = countries;
        }
        ((RecyclerViewAdapter) mRecyclerView.getAdapter()).setCountries(mCountries);
    }

    public static class Builder {

        private boolean mShowingFlag;
        private boolean mEnablingSearch;
        private boolean mShowingDialCode;
        private String mPreSelectedCountry;
        private DetectionMethod mDetectionMethod = DetectionMethod.NONE;
        private Sort mSort = Sort.NONE;
        private RecyclerViewAdapter.OnCountryClickListener mOnCountryClickListener;
        private OnAutoDetectCountryListener mOnAutoDetectCountryListener;
        private List<String> mCountries;

        private Context mContext;
        private Locale mLocale;
        private List<String> mExceptCountries;
        private int mStyle;
        private ViewType mViewType = ViewType.DIALOG;

        public Builder(Context context) {

            mContext = context;
        }

        public Builder sortBy(Sort sort) {

            mSort = sort;
            return this;
        }

        public Builder setStyle(int style) {

            mStyle = style;
            return this;
        }

        public Builder showingFlag(boolean showingFlag) {

            mShowingFlag = showingFlag;
            return this;
        }

        public Builder showingDialCode(boolean showingDialCode) {

            mShowingDialCode = showingDialCode;
            return this;
        }

        public Builder enablingSearch(boolean enablingSearch) {

            mEnablingSearch = enablingSearch;
            return this;
        }

        public Builder setCountrySelectionListener(RecyclerViewAdapter.OnCountryClickListener onCountryClickListener) {

            mOnCountryClickListener = onCountryClickListener;
            return this;
        }

        public Builder enableAutoDetectCountry(DetectionMethod detectionMethod, OnAutoDetectCountryListener onAutoDetectCountryListener) {

            mDetectionMethod = detectionMethod;
            mOnAutoDetectCountryListener = onAutoDetectCountryListener;
            return this;

        }

        public Builder setLocale(Locale locale) {

            mLocale = locale;
            return this;
        }

        public Builder setPreSelectedCountry(String preSelectedCountry) {

            mPreSelectedCountry = preSelectedCountry;
            return this;
        }

        public Builder exceptCountriesName(List<String> countriesName) {

            mExceptCountries = countriesName;
            return this;
        }

        public Builder setViewType(ViewType viewType) {

            mViewType = viewType;
            return this;
        }

        public Builder setCountries(List<String> countries) {

            if (countries == null || countries.size() < 1) {
                return this;
            }
            mCountries = countries;
            return this;
        }

        public CountryPicker build() {

            return new CountryPicker(this);
        }
    }

    public enum Sort {
        NONE,
        COUNTRY,
        CODE,
        DIALCODE
    }

    public enum ViewType {
        DIALOG,
        BOTTOMSHEET;
    }

    public enum DetectionMethod {
        NONE,
        LOCALE,
        SIM,
        NETWORK
    }
}