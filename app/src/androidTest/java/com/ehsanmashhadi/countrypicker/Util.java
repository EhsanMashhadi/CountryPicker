package com.ehsanmashhadi.countrypicker;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.view.ViewGroup;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.core.AllOf.allOf;

class Util {

    static ViewAction typeSearchViewText(final String text) {

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

    static Matcher<View> nthChildOf(final Matcher<View> parentMatcher, final int childPosition) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {

                description.appendText("with " + childPosition + " child view of type parentMatcher");
            }

            @Override
            public boolean matchesSafely(View view) {

                if (!(view.getParent() instanceof ViewGroup)) {
                    return parentMatcher.matches(view.getParent());
                }

                ViewGroup group = (ViewGroup) view.getParent();
                return parentMatcher.matches(view.getParent()) && group.getChildAt(childPosition).equals(view);
            }
        };
    }

    static Matcher<View> hasItem(Matcher<View> matcher) {

        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {

            @Override
            public void describeTo(Description description) {

                description.appendText("has item: ");
                matcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(RecyclerView view) {

                RecyclerView.Adapter adapter = view.getAdapter();
                for (int position = 0; position < adapter.getItemCount(); position++) {
                    int type = adapter.getItemViewType(position);
                    RecyclerView.ViewHolder holder = adapter.createViewHolder(view, type);
                    adapter.onBindViewHolder(holder, position);
                    if (matcher.matches(holder.itemView)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {

        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {

                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {

                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }

    public static Matcher<View> withRecyclerViewSize(final int size) {

        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(final View view) {

                final int actualListSize = ((RecyclerView) view).getAdapter().getItemCount();
                return actualListSize == size;
            }

            @Override
            public void describeTo(final Description description) {

                description.appendText("RecyclerView should have " + size + " items");
            }
        };
    }


}
