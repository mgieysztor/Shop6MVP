package com.offcasoftware.shop2.view;

import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.repository.ProductRepositoryInterface;

import org.junit.*;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by RENT on 2017-04-06.
 */
public class AddProductPresenterTest {

    @Mock
    AddProductView mAddProductView;

    @Mock
    ProductRepositoryInterface mProductRepositoryInterface;

    AddProductPresenter mAddProductPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mAddProductPresenter = new AddProductPresenter(mProductRepositoryInterface,
                Schedulers.trampoline(),
                Schedulers.trampoline());
        mAddProductPresenter.setView(mAddProductView);

    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testNullRepoThrowExpection() {
        new AddProductPresenter(null, null, null);

    }

    @Test
    public void testAddProductExceptionShowsError() throws Exception {
//        Mockito.doThrow(Exception.class).when(mProductRepositoryInterface).addProduct(any(Product.class));
        when(mProductRepositoryInterface.addProductStream(any(Product.class)))
                .thenReturn(Observable.<Void>error(new Throwable()));
        mAddProductPresenter.addProduct("Product", "1");

        verify(mAddProductView).showError();
        verify(mAddProductView, never()).closeScreen();
    }

    @Test
    public void testAddProductCloseScreen() {
        when(mProductRepositoryInterface.addProductStream(any(Product.class)))
                .thenReturn(Observable.<Void>empty());

        mAddProductPresenter.addProduct("PRODUCT", "1");

        verify(mAddProductView).closeScreen();
        verify(mAddProductView, never()).showError();
    }

}