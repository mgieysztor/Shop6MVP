package com.offcasoftware.shop2.view;

import com.offcasoftware.shop2.base.BaseView;

/**
 * Created by RENT on 2017-04-06.
 */

public interface AddProductView extends BaseView {

    void closeScreen();

    void showError();

    void enableAddButton(boolean enabled);
}
