package com.offcasoftware.shop2.view;

import android.Manifest;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.offcasoftware.shop2.R;
import com.offcasoftware.shop2.view.widget.ProductCardView;
import com.robotium.solo.Solo;

import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by RENT on 2017-04-08.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    private Solo mSolo;


    @Before
    public void setUp() {
        mSolo = new Solo (InstrumentationRegistry.getInstrumentation(),activityTestRule.getActivity());

    }

    @After
    public void tearDown(){
        mSolo.finishOpenedActivities();
    }

    @org.junit.Test
    public void testMainActivityWasOpened(){
        mSolo.unlockScreen();
        mSolo.assertCurrentActivity("MainActivity", MainActivity.class);
    }

    @Test
    public void testMainActivityList(){
        View view = mSolo.getView(R.id.product_recycler);
        assertNotNull(view);

        final RecyclerView recyclerView = (RecyclerView) view;

        ProductCardView productCardView = (ProductCardView)
                recyclerView.findViewHolderForAdapterPosition(0).itemView;

        TextView productName = (TextView) productCardView.findViewById(R.id.product_name);

        assertEquals("dom 1", productName.getText());
    }
}