package com.ehsanmashhadi.library.view;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.ehsanmashhadi.library.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MyBottomSheetDialog extends BottomSheetDialog implements BaseView {

    public MyBottomSheetDialog(@NonNull Context context) {
        super(context, R.style.BottomSheetDialog);
    }

    @Override
    public void setView(View view) {
        setContentView(view);
    }

    @Override
    public void dismissView() {
        this.dismiss();
    }

    @Override
    public void showView() {
        this.show();
    }
}