package com.offcasoftware.shop2.view.widget;

import com.offcasoftware.shop2.base.BaseView;
import com.offcasoftware.shop2.model.Product;

import java.util.List;

/**
 * Created by krzysztofjanik on 04.04.2017.
 */

public interface ProductListView extends BaseView {

    void showNoDataInfo();

    void showErrorInfo();

    void showProducts(List<Product> products);

}
