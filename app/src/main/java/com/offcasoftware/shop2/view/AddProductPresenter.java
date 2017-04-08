package com.offcasoftware.shop2.view;

import com.offcasoftware.shop2.base.BasePresenter;
import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.repository.ProductRepositoryInterface;
import com.offcasoftware.shop2.util.Precondition;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by RENT on 2017-04-06.
 */

public class AddProductPresenter extends BasePresenter<AddProductView> {

    private final ProductRepositoryInterface mProductRepositoryInterface;


    public AddProductPresenter(ProductRepositoryInterface productRepositoryInterface, Scheduler subscribe, Scheduler observe) {
        super(subscribe, observe);
        mProductRepositoryInterface = Precondition.checkNotNull(productRepositoryInterface);
    }

    public void setupView() {
        getView().enableAddButton(false);
    }

    public void onTextChanged(String name, String price) {
        final boolean validName = name != null && !name.isEmpty();
        final boolean validPrice = price != null && !price.isEmpty();

        final boolean addButtonEnabled = validName && validPrice;

        getView().enableAddButton(addButtonEnabled);

    }


    public void addProduct(String name, String price) {
        int priceInt = Integer.parseInt(price);
        Product product = new Product(name, priceInt);
//        try {
//            mProductRepositoryInterface.addProduct(product);
//            getView().closeScreen();
//        } catch (Exception e) {
//            getView().showError();
//        }
        Disposable disposable = mProductRepositoryInterface.addProductStream(product)
                .subscribeOn(mSubscribeScheduler)
                .observeOn(mObserveScheduler)
                .subscribeWith(new DisposableObserver<Void>() {
                    @Override
                    public void onNext(Void value) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError();
                    }

                    @Override
                    public void onComplete() {
                        getView().closeScreen();
                    }
                });
        addDisposable(disposable);
    }

}
