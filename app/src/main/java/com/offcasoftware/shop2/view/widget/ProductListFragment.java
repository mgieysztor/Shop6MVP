package com.offcasoftware.shop2.view.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.offcasoftware.shop2.R;
import com.offcasoftware.shop2.adapter.ProductAdapter;
import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.repository.ProductRepository;

import java.util.List;

/**
 * Created by krzysztofjanik on 13.03.2017.
 */

public class ProductListFragment extends Fragment
        implements ProductListView, ProductCardView.ProductCardViewInterface {

    @BindView(R.id.product_recycler)
    RecyclerView mRecyclerView;

    @BindView(R.id.product_info)
    TextView mStatusTextView;


    private ProductAdapter mAdapter;

    private ProductListPresenter mProductListPresenter;

    @Override
    public void showNoDataInfo() {
        mStatusTextView.setVisibility(View.VISIBLE);
        mStatusTextView.setText(R.string.StatusTextNoData);
    }

    @Override
    public void showErrorInfo() {
        mStatusTextView.setVisibility(View.VISIBLE);
        mStatusTextView.setText(R.string.StatusTextError);
    }

    @Override
    public void showProducts(List<Product> products) {
        mStatusTextView.setVisibility(View.INVISIBLE);
        if (mListener != null) {
            mListener.onProductsReady(products);
        }
        mAdapter.swapData(products);
    }

    public interface OnProductSelected {
        void onProductsReady(List<Product> products);
        void onProductSelected(Product product);
    }

    private OnProductSelected mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new ProductAdapter(getActivity(), this);
        mRecyclerView.setAdapter(mAdapter);

        mProductListPresenter = new ProductListPresenter(ProductRepository.getInstance());
        mProductListPresenter.setView(this);

        mProductListPresenter.loadProducts();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnProductSelected) {
            mListener = (OnProductSelected) context;
        }
    }

    @Override
    public void onProductClicked(Product product) {
        if (mListener != null) {
            mListener.onProductSelected(product);
        }
    }
}
