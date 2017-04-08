package com.offcasoftware.shop2.repository;

import com.offcasoftware.shop2.AndroidApplication;
import com.offcasoftware.shop2.database.Database;
import com.offcasoftware.shop2.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.internal.operators.observable.ObservableSampleTimed;

/**
 * @author maciej.pachciarek on 2017-02-18.
 */

public class ProductRepository implements ProductRepositoryInterface {

    private static ProductRepository mInstance = new ProductRepository();

    private final Database mDatabase;

    // TODO: 2017-02-21 test
    private ProductRepository() {

        mDatabase = AndroidApplication.getDatabase();

        List<Product> products = new ArrayList<>();

        Product product1 = new Product(1, "dom 1", 1000, "dom1");
        Product product2 = new Product(2, "dom 2", 2000, "dom2");
        Product product3 = new Product(3, "dom 3", 3000, "dom3");

        products.add(product1);
        products.add(product2);
        products.add(product3);

        mDatabase.saveProducts(products);
    }

    public static ProductRepositoryInterface getInstance() {
        return mInstance;
    }

    @Override
    public List<Product> getProducts() {
        return mDatabase.getProducts();
    }

    @Override
    public Product getProduct(final int productId) {
        return mDatabase.getProduct(productId);
    }

    @Override
    public void addProduct(Product product) {
        mDatabase.saveProduct(product.getName(), product.getPrice());
    }

    @Override
    public Observable<Void> addProductStream(final Product product) {
        return Observable.defer(new Callable<ObservableSource<? extends Void>>() {

            @Override
            public ObservableSource<? extends Void> call() throws Exception {
                addProduct(product);
                return Observable.empty();
            }
        });

    }
}
