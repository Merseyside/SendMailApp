package com.merseyside.admin.sendmailapp.presentation.internal.di.components;

/**
 * Created by ivan_ on 09.10.2017.
 */


import com.merseyside.admin.sendmailapp.data.Validator;
import com.merseyside.admin.sendmailapp.presentation.presenter.FillFormPresenter;
import com.merseyside.admin.sendmailapp.presentation.view.activity.BaseActivity;
import com.merseyside.admin.sendmailapp.presentation.internal.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;


@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void inject(BaseActivity baseActivity);
    void inject(Validator validator);
    void inject(FillFormPresenter fillFormPresenter);
}
