package com.ehsanmashhadi.countrypicker;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class CountryPickerTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityTestRule = new IntentsTestRule<>(MainActivity.class);


    @Test
    public void displayList_preSelected() {

        onView(withText("Guam")).check(matches(isSelected()));
    }

    @Test
    public void searchOnList_containCountry() {

        onView(withId(R.id.searchview_country)).perform(Util.typeSearchViewText("China"));
        onView(withId(R.id.recyclerview_countries)).check(matches(hasDescendant(withText("China"))));
    }


}
