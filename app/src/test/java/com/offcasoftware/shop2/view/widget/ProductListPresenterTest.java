package com.offcasoftware.shop2.view.widget;

import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.repository.ProductRepositoryInterface;

import org.junit.Before;
import org.junit.Test;

import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by RENT on 2017-04-05.
 */
public class ProductListPresenterTest {

//    ProductRepositoryInterface mRepositoryInterface = mock (ProductRepositoryInterface.class);
    @Mock
    ProductRepositoryInterface mRepositoryInterface;

    @Mock
    ProductListView mProductListView;
    private ProductListPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mPresenter = new ProductListPresenter(mRepositoryInterface);
        mPresenter.setView(mProductListView);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPresenterWithNullViewThrowException() throws Exception {
        mPresenter = new ProductListPresenter(mRepositoryInterface);
        mPresenter.setView(null);
    }

    @Test
    public void testPresenterShowsNoDataWhenEmptyList() throws Exception {
        when(mRepositoryInterface.getProducts()).thenReturn(Collections.<Product>emptyList());
        mPresenter.loadProducts();

        verify(mProductListView).showNoDataInfo();
        verify(mProductListView,never()).showErrorInfo();
        verify(mProductListView,never()).showProducts(ArgumentMatchers.<Product>anyList());
    }

    @Test
    public void testPresenterShowsDataWhenNoEmptyList() throws Exception {
        List<Product> products = new ArrayList<>();
        products.add(new Product());

        when(mRepositoryInterface.getProducts()).thenReturn(products);
        mPresenter.loadProducts();

        verify(mProductListView).showProducts(products);
        verify(mProductListView,never()).showErrorInfo();
        verify(mProductListView,never()).showNoDataInfo();

    }

    @Test
    public void testPresenterShowsDataWhenNoEmptyList_2() throws Exception {
        List<Product> products = mock(List.class);
        when(products.isEmpty()).thenReturn(false);

        when(mRepositoryInterface.getProducts()).thenReturn(products);
        mPresenter.loadProducts();

        verify(mProductListView).showProducts(products);
        verify(mProductListView,never()).showErrorInfo();
        verify(mProductListView,never()).showNoDataInfo();

    }
    @Test
    public void testPresenterShowsErrorInfoWhenRepositoryException() throws Exception {

        when(mRepositoryInterface.getProducts()).thenThrow(Exception.class);
        mPresenter.loadProducts();

        verify(mProductListView).showErrorInfo();
        verify(mProductListView,never()).showNoDataInfo();
        verify(mProductListView,never()).showProducts(ArgumentMatchers.<Product>anyList());

    }
}