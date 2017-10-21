package com.merseyside.admin.sendmailapp.presentation.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.merseyside.admin.sendmailapp.presentation.EmailApplication;
import com.merseyside.admin.sendmailapp.presentation.internal.di.components.AppComponent;
import com.merseyside.admin.sendmailapp.presentation.navigation.Navigator;

import javax.inject.Inject;

/**
 * Created by ivan_ on 20.10.2017.
 */

public class BaseActivity extends AppCompatActivity {

    @Inject
    Navigator navigator;

    @Inject
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
        EmailApplication.verifyStoragePermissions(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected AppComponent getApplicationComponent() {
        return EmailApplication.getApplicationComponent();
    }

    protected void showMessage(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }
}
