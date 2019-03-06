package com.ehsanmashhadi.library.view;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

class MyDialog extends android.app.Dialog implements BaseView {

    MyDialog(@NonNull Context context) {

        super(context);
    }

    @Override
    public void showView(View view) {

        this.setContentView(view);
        show();
    }

    @Override
    public void dismissView() {

        this.dismiss();
    }
}