package com.offcasoftware.shop2.view.widget;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.BindDimen;
import com.offcasoftware.shop2.R;
import com.offcasoftware.shop2.model.Product;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author maciej.pachciarek on 2017-02-18.
 */

public class ProductCardView extends CardView {

    public interface ProductCardViewInterface {

        void onProductClicked(Product product);
    }

    @BindDimen(R.dimen.product_list_item_height)
    int mProductListItemHeight;

    @BindView(R.id.product_image)
    ImageView mProductImage;

    @BindView(R.id.product_name)
    TextView mProductName;

    @BindView(R.id.product_price)
    TextView mProductPrice;

    public ProductCardView(final Context context) {
        this(context, null);
    }

    public ProductCardView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProductCardView(final Context context, final AttributeSet attrs,
            final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void bindTo(final Product product,
            final ProductCardViewInterface productCardViewInterface) {
        mProductName.setText(product.getName());
        mProductPrice.setText(String.valueOf(product.getPrice()));
        if (!TextUtils.isEmpty(product.getImageResId())) {
            int drawableResourceId = this.getResources()
                    .getIdentifier(product.getImageResId(), "drawable",
                            getContext().getPackageName());
            mProductImage.setImageResource(drawableResourceId);
        }

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                productCardViewInterface.onProductClicked(product);
            }
        });
    }

    private void init() {
        inflate(getContext(), R.layout.view_product_card_view, this);
        ButterKnife.bind(this);
    }
}

