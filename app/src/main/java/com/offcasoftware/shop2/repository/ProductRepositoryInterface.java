package com.offcasoftware.shop2.repository;

import android.support.annotation.NonNull;
import com.offcasoftware.shop2.model.Product;

import java.util.List;

import io.reactivex.Observable;


/**
 * @author maciej.pachciarek on 2017-02-18.
 */

public interface ProductRepositoryInterface {

    @NonNull
    List<Product> getProducts() throws Exception;

//    Observable<List<Product>> getStreamProducts();

    Product getProduct(int productId);

    void addProduct(Product product) throws Exception;

    Observable<Void>addProductStream(Product product);


}
