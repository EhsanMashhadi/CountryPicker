package com.ehsanmashhadi.library;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CountryHolder> {

    public interface OnCountryClickListener {
        void onCountrySelected(Country country);
    }

    private List<Country> mCountryList;
    private OnCountryClickListener mOnCountryClickListener;

    RecyclerViewAdapter(List<Country> countryList) {

        mCountryList = countryList;
    }

    public void setListener(OnCountryClickListener onCountryClickListener) {

        mOnCountryClickListener = onCountryClickListener;

    }

    @NonNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_country, parent, false);
        return new CountryHolder(itemView, position -> mOnCountryClickListener.onCountrySelected(mCountryList.get(position)));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryHolder holder, int position) {

        holder.mTextViewName.setText(mCountryList.get(position).getName());
        holder.mTextViewCode.setText(mCountryList.get(position).getDialCode());
        holder.mImageViewFlag.setImageResource(android.R.drawable.ic_input_add);
    }

    @Override
    public int getItemCount() {

        return mCountryList.size();
    }

    static class CountryHolder extends RecyclerView.ViewHolder {

        private interface OnItemClickListener {
            void onItemSelected(int position);
        }

        private TextView mTextViewName;
        private TextView mTextViewCode;
        private ImageView mImageViewFlag;

        CountryHolder(@NonNull View itemView, final OnItemClickListener onItemClickListener) {

            super(itemView);
            mTextViewName = itemView.findViewById(R.id.textview_name);
            mTextViewCode = itemView.findViewById(R.id.textview_code);
            mImageViewFlag = itemView.findViewById(R.id.imageview_flag);
            itemView.setOnClickListener(v -> onItemClickListener.onItemSelected(getAdapterPosition()));

        }
    }
}
