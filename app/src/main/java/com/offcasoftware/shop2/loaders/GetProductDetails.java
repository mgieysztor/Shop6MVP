package com.offcasoftware.shop2.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.repository.ProductRepository;
import com.offcasoftware.shop2.repository.ProductRepositoryInterface;

/**
 * Created by krzysztofjanik on 16.03.2017.
 */

public class GetProductDetails extends AsyncTaskLoader<Product> {

    private final int mProductId;

    private final ProductRepositoryInterface mProductRepository
            = ProductRepository.getInstance();

    public GetProductDetails(Context context, int id) {
        super(context);
        mProductId = id;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
    }

    @Override
    public Product loadInBackground() {
        final Product product =  mProductId != Product.UNDEFINED
                ? mProductRepository.getProduct(mProductId) : null;
        return product;
    }
}
