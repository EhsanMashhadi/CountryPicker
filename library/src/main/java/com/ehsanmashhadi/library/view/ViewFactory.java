package com.ehsanmashhadi.library.view;

import androidx.appcompat.app.AppCompatActivity;

class ViewFactory {

    static BaseView create(CountryPicker.ViewType viewType, AppCompatActivity activity) {

        switch (viewType) {

            case DIALOG:
                return new MyDialog(activity);

            case BOTTOMSHEET:
                BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
                bottomSheetFragment.setActivity(activity);
                return bottomSheetFragment;

            default:
                return null;
        }
    }
}