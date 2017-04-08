package com.offcasoftware.shop2.view;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.offcasoftware.shop2.R;
import com.offcasoftware.shop2.adapter.ProductPageAdapter;
import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.repository.ProductRepository;
import com.offcasoftware.shop2.repository.ProductRepositoryInterface;

import java.util.List;

public class ProductPagerActivity extends AppCompatActivity {

    @BindView(R.id.product_pager)
    ViewPager mViewPager;

    private ProductRepositoryInterface mProductRepository
            = ProductRepository.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_pager);
        ButterKnife.bind(this);

        List<Product> products = null;
        try {
            products = mProductRepository.getProducts();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mViewPager.setAdapter(
                new ProductPageAdapter(getSupportFragmentManager(), products));
        //mViewPager.setCurrentItem(2, true);
        //mViewPager.setOffscreenPageLimit(2);
    }
}
