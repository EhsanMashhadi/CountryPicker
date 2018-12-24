package com.ehsanmashhadi.library.view;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ehsanmashhadi.library.R;
import com.ehsanmashhadi.library.model.Country;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CountryHolder> {

    public interface OnCountryClickListener {
        void onCountrySelected(Country country);
    }

    private List<Country> mCountryList;
    private OnCountryClickListener mOnCountryClickListener;
    private static int sStyle;
    private static boolean sShowingFlag;
    private static boolean sShowingDialCode;


    RecyclerViewAdapter(List<Country> countryList, @StyleRes int style, boolean showingFlag, boolean showingDialCode) {

        mCountryList = countryList;
        sStyle = style;
        sShowingFlag = showingFlag;
        sShowingDialCode = showingDialCode;
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

            TypedArray typedArray = itemView.getContext().obtainStyledAttributes(sStyle, R.styleable.CountryPickerStyleable);

            int rowBackgroundColor = typedArray.getResourceId(R.styleable.CountryPickerStyleable_rowBackgroundColor, Color.WHITE);
            int countryNameColor = typedArray.getResourceId(R.styleable.CountryPickerStyleable_countryNameColor, Color.BLACK);
            int dialCodeColor = typedArray.getResourceId(R.styleable.CountryPickerStyleable_dialCodeColor, Color.BLACK);

            mTextViewName = itemView.findViewById(R.id.textview_name);
            mTextViewCode = itemView.findViewById(R.id.textview_code);
            mImageViewFlag = itemView.findViewById(R.id.imageview_flag);

            if (!sShowingFlag) {
                mImageViewFlag.setVisibility(View.GONE);
            }
            if (!sShowingDialCode) {
                mTextViewCode.setVisibility(View.GONE);
            }

            mTextViewName.setTextColor(ContextCompat.getColor(itemView.getContext(), countryNameColor));
            mTextViewCode.setTextColor(ContextCompat.getColor(itemView.getContext(), dialCodeColor));
            itemView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), rowBackgroundColor));

            itemView.setOnClickListener(v -> onItemClickListener.onItemSelected(getAdapterPosition()));

        }
    }
}
