package com.offcasoftware.shop2.view.widget;

import com.offcasoftware.shop2.base.BasePresenter;
import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.repository.ProductRepositoryInterface;

import java.util.List;

/**
 * Created by krzysztofjanik on 04.04.2017.
 */

public class ProductListPresenter extends BasePresenter<ProductListView> {

    private final ProductRepositoryInterface mProductRepository;

    public ProductListPresenter(ProductRepositoryInterface repository) {
        mProductRepository = repository;
    }

    public void loadProducts() {
        try {
            List<Product> list = null;
            list = mProductRepository.getProducts();
            if (list.isEmpty()){
                getView().showNoDataInfo();
            }else {
                getView().showProducts(list);
            }
        } catch (Exception e) {
            getView().showErrorInfo();

        }


    }

}
