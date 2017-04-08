package com.offcasoftware.shop2.base;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by krzysztofjanik on 04.04.2017.
 */

public abstract class BasePresenter<V extends BaseView> {

    private V mView;

    protected Scheduler mSubscribeScheduler;
    protected Scheduler mObserveScheduler;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public BasePresenter(){

    }

    public BasePresenter(Scheduler subscribe, Scheduler observe){
        mSubscribeScheduler = subscribe;
        mObserveScheduler = observe;
    }

    public void setView(V view) {
        if (view == null){
            throw new IllegalArgumentException("No null view in Presenter");
        }
        mView = view;
    }

    public V getView() {
        return mView;
    }

    public void addDisposable (Disposable disposable){
        mCompositeDisposable.add(disposable);
    }

    public void clean(){
        mCompositeDisposable.clear();

    }
}
