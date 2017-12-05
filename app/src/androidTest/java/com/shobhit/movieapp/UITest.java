package com.shobhit.movieapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.shobhit.movieapp.mainModule.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by abcplusd on 05/12/17.
 */

@RunWith(AndroidJUnit4.class)
public class UITest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);


    @Test
    public void testImageOnClick() {
        onView(withId(R.id.gridview)).perform(RecyclerViewActions.scrollToPosition(14));
        sleepThread();
        onView(withId(R.id.gridview)).perform(RecyclerViewActions.scrollToPosition(0));
        sleepThread();
        onView(withId(R.id.gridview)).perform(RecyclerViewActions.scrollToPosition(10));
        sleepThread();
        onView(withId(R.id.gridview)).perform(RecyclerViewActions.actionOnItemAtPosition(10, click()));
        sleepThread();
    }

    @Test
    public void testSpinnerOnClick() {
        onView(withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Sort By Rating"))).perform(click());
        onView(withId(R.id.spinner)).check(matches(withSpinnerText(containsString("Sort By Rating"))));
    }


    public void sleepThread() {
        try {
            Thread.sleep(400);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
