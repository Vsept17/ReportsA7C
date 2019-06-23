package com.a7c.reportsa7c.AdminActivity.AHPPrjoect.assistmethod;

import com.ahpandroid.BasePresenter;
import com.ahpandroid.BaseView;

public interface SelectAssistMethodContract {

    interface View extends BaseView<Presenter> {
        void goToAHP();
        void goToRegression();
        void goToProgression();
    }

    interface Presenter extends BasePresenter {}
}
