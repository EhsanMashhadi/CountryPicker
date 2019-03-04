package com.ehsanmashhadi.library.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BottomSheetFragment extends BottomSheetDialogFragment implements BaseView {

    private View mView;
    private AppCompatActivity mActivityCompat;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return mView;
    }

    @Override
    public void showView(View view) {

        mView = view;
        this.show(mActivityCompat.getSupportFragmentManager(), "bottomsheet");
    }

    public void setActivity(AppCompatActivity appCompatActivity) {

        mActivityCompat = appCompatActivity;
    }
}