package com.ehsanmashhadi.library.view;


import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;

import com.ehsanmashhadi.library.R;
import com.ehsanmashhadi.library.model.Country;
import com.ehsanmashhadi.library.presenter.CountryPickerContractor;
import com.ehsanmashhadi.library.presenter.CountryPickerPresenter;
import com.ehsanmashhadi.library.repository.ResourceCountryRepository;

import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CountryPicker implements CountryPickerContractor.View {

    private Sort mSort;
    private ViewType mViewType;
    private boolean mShowingFlag = true;
    private boolean mEnablingSearch = true;
    private boolean mShowingDialCode = true;

    private Context mContext;
    private List<Country> mCountries;
    private List<String> mExceptCountriesName;
    private Locale mLocale;
    private RecyclerView mRecyclerView;
    private SearchView mSearchViewCountry;
    private int mStyle;

    private RecyclerViewAdapter.OnCountryClickListener mOnCountryClickListener;
    private View mView;
    private CountryPickerContractor.Presenter mPresenter;

    private CountryPicker() {

    }

    public CountryPicker(Builder builder) {

        initAttributes(builder);
        mPresenter = new CountryPickerPresenter(new ResourceCountryRepository(builder.mContext.getResources()), this);
        initLocale();
        initView();
        initCountries();
        sort();
        initSearchView();
    }

    private void sort() {

        mPresenter.sort(mSort);
    }

    private void initAttributes(Builder builder) {

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
        if (mStyle == 0) {
            mStyle = R.style.CountryPickerLightStyle;
        }
    }

    private void initView() {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        mView = layoutInflater.inflate(R.layout.layout_countrypicker, null);
        mRecyclerView = mView.findViewById(R.id.recyclerview_countries);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(mCountries, mStyle, mShowingFlag, mShowingDialCode);
        if (mOnCountryClickListener != null)
            recyclerViewAdapter.setListener(mOnCountryClickListener);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(recyclerViewAdapter);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void initSearchView() {

        mSearchViewCountry = mView.findViewById(R.id.searchview_country);
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

    public void show(AppCompatActivity activity) {

        BaseView baseView = ViewFactory.create(mViewType, activity);
        baseView.showView(mView);
    }

    @Override
    public void setCountries(List<Country> countries) {

        mCountries = countries;
        ((RecyclerViewAdapter) mRecyclerView.getAdapter()).setCountries(countries);
    }

    public static class Builder {

        private boolean mShowingFlag;
        private boolean mEnablingSearch;
        private boolean mShowingDialCode;
        private Sort mSort = Sort.NONE;
        private RecyclerViewAdapter.OnCountryClickListener mOnCountryClickListener;
        private Context mContext;
        private Locale mLocale;
        private List<String> mExceptCountries;
        private int mStyle;
        private ViewType mViewType;

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

        public Builder setListener(RecyclerViewAdapter.OnCountryClickListener onCountryClickListener) {

            mOnCountryClickListener = onCountryClickListener;
            return this;
        }

        public Builder setLocale(Locale locale) {

            mLocale = locale;
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

        public CountryPicker build() {

            return new CountryPicker(this);
        }
    }

    public enum Sort {
        NONE,
        COUNTRY,
        CODE
    }

    public enum ViewType {
        DIALOG,
        BOTTOMSHEET;
    }
}