package com.ehsanmashhadi.countrypicker;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class CountryPickerTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityTestRule = new IntentsTestRule<>(MainActivity.class);


//    @Test
//    public void displayList_preSelected() {
//
//        onView(withText("Guam")).check(matches(isSelected()));
//    }

//    @Test
//    public void searchOnList_containCountry() {
//
//        onView(withId(R.id.searchview_country)).perform(Util.typeSearchViewText("China"));
//        onView(withId(R.id.recyclerview_countries)).check(matches(hasDescendant(withText("China"))));
//    }

    @Test
    public void filterList_showFlag() {

        onView(withId(R.id.switch_flags)).check(matches(isNotChecked())).perform(click());
        onView(withId(R.id.button_display)).perform(click());
        onView(withId(R.id.recyclerview_countries))
                .check(matches(hasDescendant(allOf(withId(R.id.imageview_flag), isDisplayed()))));
    }

    @Test
    public void filterList_showDialCode() {

        onView(withId(R.id.switch_dialcodes)).check(matches(isNotChecked())).perform(click());
        onView(withId(R.id.button_display)).perform(click());
        onView(withId(R.id.recyclerview_countries))
                .check(matches(hasDescendant(allOf(withId(R.id.textview_code), isDisplayed()))));
    }

    @Test
    public void displayList_enableSearch() {

        onView(withId(R.id.switch_search)).check(matches(isNotChecked())).perform(click());
        onView(withId(R.id.button_display)).perform(click());
        onView(withId(R.id.searchview_country)).check(matches(isDisplayed()));
    }


    @Test
    public void displayList_persianLocale() {

        onView(withId(R.id.spinner_locale)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("FA"))).perform(click());
        onView(withId(R.id.button_display)).perform(click());
        onView(withId(R.id.recyclerview_countries)).check(matches(hasDescendant(withText("افغانستان"))));
    }

    @Test
    public void displayList_englishLocale() {

        onView(withId(R.id.spinner_locale)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("EN"))).perform(click());
        onView(withId(R.id.button_display)).perform(click());
        onView(withId(R.id.recyclerview_countries)).check(matches(hasDescendant(withText("Afghanistan"))));
    }
}
