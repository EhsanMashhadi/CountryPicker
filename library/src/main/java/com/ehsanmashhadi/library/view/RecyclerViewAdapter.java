package com.ehsanmashhadi.library.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ehsanmashhadi.library.R;
import com.ehsanmashhadi.library.model.Country;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CountryHolder> {

    public interface OnCountryClickListener {
        void onCountrySelected(Country country);

    }

    private List<Country> mCountryList;
    private OnCountryClickListener mOnCountryClickListener;
    private static boolean sShowingFlag;
    private static boolean sShowingDialCode;
    private static String sPreselectCountry;

    RecyclerViewAdapter(List<Country> countryList, String preselectCountry, boolean showingFlag, boolean showingDialCode) {

        mCountryList = countryList;
        sShowingFlag = showingFlag;
        sShowingDialCode = showingDialCode;
        sPreselectCountry = preselectCountry;
    }

    void setListener(OnCountryClickListener onCountryClickListener) {

        mOnCountryClickListener = onCountryClickListener;

    }

    @NonNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_country, parent, false);
        return new CountryHolder(itemView, position -> {
            mOnCountryClickListener.onCountrySelected(mCountryList.get(position));
            itemView.setSelected(true);
            sPreselectCountry = mCountryList.get(position).getName();
            notifyDataSetChanged();
        });
    }

    @Override
    public void onBindViewHolder(@NonNull CountryHolder holder, int position) {

        holder.mTextViewName.setText(mCountryList.get(position).getName());
        holder.mTextViewCode.setText(mCountryList.get(position).getDialCode());


        int resourceId = holder.itemView.getContext().getResources().getIdentifier(mCountryList.get(position).
                getFlagName(), "drawable", holder.itemView.getContext().getPackageName());
        holder.mImageViewFlag.setImageResource(resourceId);
        if (sPreselectCountry != null)
            if (mCountryList.get(position).getName().toLowerCase().equals(sPreselectCountry.toLowerCase())) {
                holder.itemView.setSelected(true);
            } else {
                holder.itemView.setSelected(false);
            }
    }

    @Override
    public int getItemCount() {

        return mCountryList.size();
    }

    public void setCountries(List<Country> countries) {

        mCountryList = countries;
        notifyDataSetChanged();
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

            if (sShowingFlag) {
                mImageViewFlag.setVisibility(View.GONE);
            }
            if (!sShowingDialCode) {
                mTextViewCode.setVisibility(View.GONE);
            }
            itemView.setOnClickListener(v -> onItemClickListener.onItemSelected(getAdapterPosition()));
        }
    }
}
