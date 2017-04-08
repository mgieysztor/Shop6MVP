package com.offcasoftware.shop2.view;

import android.content.ContentUris;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.offcasoftware.shop2.R;
import com.offcasoftware.shop2.loaders.GetProductDetails;
import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.provider.ProductProvider;
import com.offcasoftware.shop2.repository.ProductRepository;
import com.offcasoftware.shop2.repository.ProductRepositoryInterface;

import java.util.List;

/**
 * Created by krzysztofjanik on 13.03.2017.
 */

public class ProductDetailsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String INTENT_PRODUCT_ID =
            ProductDetailsActivity.class.getSimpleName() + "productId";

    @BindView(R.id.product_image)
    ImageView mProductImage;

    @BindView(R.id.product_name)
    TextView mProductName;

    @BindView(R.id.product_price)
    TextView mProductPrice;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public static ProductDetailsFragment newInstance(int id) {
        ProductDetailsFragment fragment = new ProductDetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(INTENT_PRODUCT_ID, id);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_product_details, container, false);
        ButterKnife.bind(this, view);

        return view;    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(2, getArguments(), this);
    }

    public void updateProduct(Product product) {
        displayData(product);
    }

    private void displayData(final Cursor cursor) {
        if (cursor == null) {
            return;
        }
        cursor.moveToFirst();
        final Product product = new Product(cursor);
        cursor.close();
        displayData(product);
    }

    private void displayData(Product product) {
        int drawableResourceId = this.getResources()
                .getIdentifier(product.getImageResId(), "drawable",
                        getActivity().getPackageName());
        mProductImage.setImageResource(drawableResourceId);
        mProductName.setText(product.getName());
        mProductPrice.setText(String.valueOf(product.getPrice()));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        int productId = bundle != null ? bundle.getInt(INTENT_PRODUCT_ID, Product.UNDEFINED)
                :Product.UNDEFINED;
        return new CursorLoader(getActivity(),
                ContentUris.withAppendedId(ProductProvider.CONTENT_URI, productId),
                null, null, null, null);
        //new GetProductDetails(getActivity(), productId);
    }

    @Override
    public void onLoadFinished(Loader loader, Cursor cursor) {
        displayData(cursor);
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
}
