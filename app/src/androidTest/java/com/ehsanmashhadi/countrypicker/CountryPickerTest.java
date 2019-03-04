package com.ehsanmashhadi.countrypicker;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.hasTextColor;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.ehsanmashhadi.countrypicker.Util.hasItem;
import static com.ehsanmashhadi.countrypicker.Util.nthChildOf;
import static com.ehsanmashhadi.countrypicker.Util.withIndex;
import static com.ehsanmashhadi.countrypicker.Util.withRecyclerViewSize;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class CountryPickerTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityTestRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void displayList_countriesLength() {

        onView(withId(R.id.button_display)).perform(click());
        onView(withId(R.id.recyclerview_countries)).check(matches(withRecyclerViewSize(244)));
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(withId(R.id.spinner_locale)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("FA"))).perform(click());
        onView(withId(R.id.button_display)).perform(click());
        onView(withId(R.id.recyclerview_countries)).check(matches(withRecyclerViewSize(244)));
    }

    @Test
    public void searchOnList_containCountry() {

        onView(withId(R.id.switch_search)).check(matches(isNotChecked())).perform(click());
        onView(withId(R.id.button_display)).perform(click());
        onView(withId(R.id.searchview_country)).perform(Util.typeSearchViewText("China"));
        onView(withId(R.id.recyclerview_countries)).check(matches(hasDescendant(withText("China"))));
    }

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

    @Test
    public void displayList_preselectedCountry() {

        onView(withId(R.id.edittext_preselectedcountry)).perform(typeText("United States"));
        onView(withId(R.id.edittext_preselectedcountry)).perform(closeSoftKeyboard());

        onView(withId(R.id.button_display)).perform(click());
        onView(withId(R.id.recyclerview_countries)).check(matches(hasDescendant(withText("United States"))));
        onView(withId(R.id.recyclerview_countries))
                .check(matches(hasDescendant(allOf(withText("United States"), isSelected()))));
    }

    @Test
    public void displayList_sortByCode() {

        onView(withId(R.id.spinner_sort)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("CODE"))).perform(click());
        onView(withId(R.id.button_display)).perform(click());
        onView(nthChildOf(withId(R.id.recyclerview_countries), 0)).check(matches(hasDescendant(withText("Andorra"))));
    }

    @Test
    public void displayList_sortByCountry() {

        onView(withId(R.id.spinner_sort)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("COUNTRY"))).perform(click());
        onView(withId(R.id.button_display)).perform(click());
        onView(nthChildOf(withId(R.id.recyclerview_countries), 0)).check(matches(hasDescendant(withText("Afghanistan"))));
    }

    @Test
    public void displayList_sortByDialCode() {

        onView(withId(R.id.spinner_sort)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("DIALCODE"))).perform(click());
        onView(withId(R.id.button_display)).perform(click());
        onView(nthChildOf(withId(R.id.recyclerview_countries), 0)).check(matches(hasDescendant(withText("American Samoa"))));
    }

    @Test
    public void displayList_exceptCountries() {

        onView(withId(R.id.edittext_exceptcountries)).perform(typeText("afghanistan,ALBANIA,algERIA"));
        onView(withId(R.id.edittext_exceptcountries)).perform(closeSoftKeyboard());
        onView(withId(R.id.button_display)).perform(click());
        onView(withId(R.id.recyclerview_countries)).check(matches(not(hasItem(hasDescendant(withText("Afghanistan"))))));
        onView(withId(R.id.recyclerview_countries)).check(matches(not(hasItem(hasDescendant(withText("Albania"))))));
        onView(withId(R.id.recyclerview_countries)).check(matches(not(hasItem(hasDescendant(withText("Algeria"))))));
    }

    @Test
    public void displayList_wantedCountries() {

        onView(withId(R.id.edittext_wantedcountries)).perform(typeText("iran,gerMANY,ITALY"));
        onView(withId(R.id.edittext_wantedcountries)).perform(closeSoftKeyboard());
        onView(withId(R.id.spinner_sort)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("COUNTRY"))).perform(click());
        onView(withId(R.id.button_display)).perform(click());
        onView(nthChildOf(withId(R.id.recyclerview_countries), 0)).check(matches(hasDescendant(withText("Iran"))));
        onView(nthChildOf(withId(R.id.recyclerview_countries), 1)).check(matches(hasDescendant(withText("Germany"))));
        onView(nthChildOf(withId(R.id.recyclerview_countries), 2)).check(matches(hasDescendant(withText("Italy"))));
    }

    @Test
    public void setStyle_dark() {

        onView(withId(R.id.spinner_style)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("CountryPickerDarkStyle"))).perform(click());
        onView(withId(R.id.button_display)).perform(click());
        onView(withIndex(withId(R.id.textview_name), 0)).check(matches(hasTextColor(R.color.countryNameColorOnDark)));
        onView(withIndex(withId(R.id.textview_code), 0)).check(matches(hasTextColor(R.color.dialCodeColorOnDark)));
    }

    @Test
    public void setStyle_light() {

        onView(withId(R.id.spinner_style)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("CountryPickerLightStyle"))).perform(click());
        onView(withId(R.id.button_display)).perform(click());
        onView(withIndex(withId(R.id.textview_name), 0)).check(matches(hasTextColor(R.color.countryNameColorOnLight)));
        onView(withIndex(withId(R.id.textview_code), 0)).check(matches(hasTextColor(R.color.dialCodeColorOnLight)));
    }
}
