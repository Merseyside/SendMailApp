package com.merseyside.admin.sendmailapp.presentation.view.activity;

import com.merseyside.admin.sendmailapp.data.entity.InputData;

/**
 * Created by ivan_ on 20.10.2017.
 */

public interface FillFormView {
    void onSuccess(InputData data);
    void onError(String msg);
}
