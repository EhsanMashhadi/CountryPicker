package com.ehsanmashhadi.library;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CountryHolder> {

    private List<Country> mCountryList;

    public RecyclerViewAdapter(List<Country> countryList) {

        mCountryList = countryList;
    }

    @NonNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new CountryHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryHolder holder, int position) {

        holder.mTextViewName.setText(mCountryList.get(position).getName());
        holder.mTextViewCode.setText(mCountryList.get(position).getCode());
    }

    @Override
    public int getItemCount() {

        return mCountryList.size();
    }

    static class CountryHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewName;
        private TextView mTextViewCode;

        CountryHolder(@NonNull View itemView) {

            super(itemView);
            mTextViewName = itemView.findViewById(R.id.textview_name);
            mTextViewCode = itemView.findViewById(R.id.textview_code);
        }
    }
}
