package com.telenor.herokuaapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.telenor.herokuaapp.view.HomeActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class HomeScreenTestCase {

    @Rule
    public ActivityTestRule<HomeActivity> homeActivityActivityTestRule = new ActivityTestRule<HomeActivity>(HomeActivity.class);

    private HomeActivity homeActivity = null;


    @Before
    public void setUp() throws Exception {
        homeActivity = homeActivityActivityTestRule.getActivity();
    }

    @Test
    public void checkViewRendered() {
        assertNotNull(homeActivity.findViewById(R.id.relativelayout_home));
        assertNotNull(homeActivity.findViewById(R.id.toolbar));
        assertNotNull(homeActivity.findViewById(R.id.rv_films));
    }

    @Test
    public void checkViewNotNull() {
        ViewInteraction recyclerView = onView(allOf(withId(R.id.rv_films)));
        assertNotNull(recyclerView);
    }


    @Test
    public void isItemClicked() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rv_films),
                        childAtPosition(
                                withId(R.id.srl_films),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(3, click()));
    }

    @Test
    public void checkPerformClickAndMoveToDetail() {
        ViewInteraction recyclerView = onView(allOf(withId(R.id.rv_films)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
    }

    @Test
    public void checkPressBackAndExit() {
        pressBack();

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("Yes"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton.perform(scrollTo(), click());
    }

    @After
    public void tearDown() throws Exception {
        homeActivity = null;
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

}
