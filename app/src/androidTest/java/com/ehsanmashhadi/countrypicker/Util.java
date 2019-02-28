package com.ehsanmashhadi.countrypicker;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

import androidx.appcompat.widget.SearchView;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.core.AllOf.allOf;

public class Util {

    public static ViewAction typeSearchViewText(final String text) {

        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                //Ensure that only apply if it is a SearchView and if it is visible.
                return allOf(isDisplayed(), isAssignableFrom(SearchView.class));
            }

            @Override
            public String getDescription() {

                return "Change view text";
            }

            @Override
            public void perform(UiController uiController, View view) {

                ((SearchView) view).setQuery(text, false);
            }
        };
    }
}
