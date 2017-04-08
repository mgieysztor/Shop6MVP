package com.offcasoftware.shop2.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.offcasoftware.shop2.R;
import com.offcasoftware.shop2.repository.ProductRepository;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author maciej.pachciarek on 2017-02-20.
 */

public class AddProductActivity extends AppCompatActivity implements AddProductView {

    @BindView(R.id.product_name)
    EditText mProductName;

    @BindView(R.id.product_price)
    EditText mProductPrice;

    @BindView(R.id.button_add_product)
    TextView mAddProductButton;

    private final TextWatcher mTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            final String name = mProductName.getText().toString();
            final String price = mProductPrice.getText().toString();

            mAddProductPresenter.onTextChanged(name, price);
        }
    };

    private AddProductPresenter mAddProductPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);

        mProductName.addTextChangedListener(mTextWatcher);
        mProductPrice.addTextChangedListener(mTextWatcher);

        mAddProductPresenter = new AddProductPresenter(ProductRepository.getInstance(),
                Schedulers.io(), AndroidSchedulers.mainThread());
        mAddProductPresenter.setView(this);
        mAddProductButton.setEnabled(false); // TODO: 2017-04-08 tu coś nie tak 
    }

    @OnClick(R.id.button_add_product)
    public void onAddProductClicked(View view) {
        String name = mProductName.getText().toString().trim();
        String price = mProductPrice.getText().toString().trim();

        mAddProductPresenter.addProduct(name, price);
        mAddProductPresenter.setView(this);


    }

    @OnClick(R.id.product_available)
    public void onDataPickerClick(View view) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog =
                new DatePickerDialog(this, dataPickerListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private final DatePickerDialog.OnDateSetListener dataPickerListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year,
                                      int month, int dayOfMonth) {
                }
            };

    @Override
    public void closeScreen() {
        onBackPressed();
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
    }


    @Override
    public void enableAddButton(boolean enabled) {
        mAddProductButton.setEnabled(true); // TODO: 2017-04-08
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAddProductPresenter.clean();
    }
}
