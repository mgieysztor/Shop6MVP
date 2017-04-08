package com.offcasoftware.shop2.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.repository.ProductRepository;
import com.offcasoftware.shop2.repository.ProductRepositoryInterface;

import java.util.List;

/**
 * Created by krzysztofjanik on 16.03.2017.
 */

public class GetAllProducts extends AsyncTaskLoader<List<Product>> {

    private List<Product> mCache;

    private final ProductRepositoryInterface mProductRepository
            = ProductRepository.getInstance();

    public GetAllProducts(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (mCache != null) {
            deliverResult(mCache);
        } else {
            forceLoad();
        }
        onContentChanged();
    }

    @Override
    public List<Product> loadInBackground() {
        //return mProductRepository.getProducts();
        List<Product> productList = null;
        try {
            productList = mProductRepository.getProducts();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public void deliverResult(List<Product> data) {
        mCache = data;
        super.deliverResult(data);
    }
}
