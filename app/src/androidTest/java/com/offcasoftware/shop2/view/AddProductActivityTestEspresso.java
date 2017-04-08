package com.offcasoftware.shop2.view;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.offcasoftware.shop2.R;

import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by RENT on 2017-04-08.
 */
@RunWith(AndroidJUnit4.class)
public class AddProductActivityTestEspresso {


    @Rule
    public ActivityTestRule<AddProductActivity> mActivityRule = new ActivityTestRule(AddProductActivity.class);

    @Test
    public void testAddProductIsDiplayed(){
        onView(withId(R.id.product_name)).check(matches(isDisplayed()));
        onView(withId(R.id.product_price)).check(matches(isDisplayed()));
    }

    @Test
    public void testAddProduct(){
        onView(withId(R.id.product_name)).check(matches(isDisplayed()));
        onView(withId(R.id.product_price)).check(matches(isDisplayed()));

        onView(withId(R.id.product_name)).perform(typeText("Product name"), closeSoftKeyboard());
        onView(withId(R.id.product_price)).perform(typeText("250000"),closeSoftKeyboard());

        onView(withId(R.id.button_add_product)).perform(click());

    }
}