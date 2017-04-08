package com.offcasoftware.shop2.view;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.EditText;

import com.offcasoftware.shop2.R;
import com.robotium.solo.Solo;

import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by RENT on 2017-04-08.
 */

@RunWith(AndroidJUnit4.class)
public class AddProductActivityTest {

    private static final String PRODUCT_NAME = "Product 1";
    private static final String PRODUCT_PRICE = "150000";

    @Rule
    public ActivityTestRule<AddProductActivity> activityTestRule = new ActivityTestRule<>(AddProductActivity.class);
    private Solo mSolo;

    @Before
    public void setUp() {
        mSolo = new Solo(InstrumentationRegistry.getInstrumentation(),
                activityTestRule.getActivity());
    }

    @After
    public void tearDown() {
        mSolo.finishOpenedActivities();
    }

    @Test
    public void testAddProductActivityWasOpened() {

        mSolo.unlockScreen();
        mSolo.assertCurrentActivity("AddProductActivity", AddProductActivity.class);
    }

    @Test
    public void testAddProductActivityHasProperElements() {
        View productName = mSolo.getView(R.id.product_name);
        assertTrue(productName.isShown());

        View productPrice = mSolo.getView(R.id.product_price);
        assertTrue(productPrice.isShown());


        View addProduct = mSolo.getView(R.id.button_add_product);
        assertTrue(addProduct.isShown());
    }

    @Test
    public void testAddProduct() throws InterruptedException {
        EditText productName = (EditText) mSolo.getView(R.id.product_name);
        mSolo.enterText(productName, PRODUCT_NAME);

        EditText productPrice = (EditText) mSolo.getView(R.id.product_price);
        mSolo.enterText(productPrice, PRODUCT_PRICE);

        View addProduct = mSolo.getView(R.id.button_add_product);
        mSolo.clickOnView(addProduct);
    }

    @Test
    public void testAddProductIsEmptyPriceIsNotAllowed() {
        EditText productName = (EditText) mSolo.getView(R.id.product_name);
        mSolo.enterText(productName, PRODUCT_NAME);

        View addProduct = mSolo.getView(R.id.button_add_product);
        assertFalse(addProduct.isEnabled());

        mSolo.takeScreenshot();
    }

}