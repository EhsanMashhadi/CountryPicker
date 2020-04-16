package com.ehsanmashhadi.library.view;

import androidx.appcompat.app.AppCompatActivity;

class ViewFactory {

    static BaseView create(CountryPicker.ViewType viewType, AppCompatActivity activity) {

        switch (viewType) {

            case DIALOG:
                return new MyDialog(activity);

            case BOTTOMSHEET:
                MyBottomSheetDialog bottomSheetDialog = new MyBottomSheetDialog(activity);
                return bottomSheetDialog;

            default:
                return null;
        }
    }
}