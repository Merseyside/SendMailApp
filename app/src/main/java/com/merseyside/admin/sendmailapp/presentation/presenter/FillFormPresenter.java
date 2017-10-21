package com.merseyside.admin.sendmailapp.presentation.presenter;

import android.content.Context;

import com.merseyside.admin.sendmailapp.data.Validator;
import com.merseyside.admin.sendmailapp.data.entity.InputData;
import com.merseyside.admin.sendmailapp.data.exception.ErrorMsgFactory;
import com.merseyside.admin.sendmailapp.presentation.EmailApplication;
import com.merseyside.admin.sendmailapp.presentation.view.activity.FillFormView;

import javax.inject.Inject;

/**
 * Created by ivan_ on 20.10.2017.
 */

public class FillFormPresenter {

    @Inject
    Context context;
    private FillFormView mListener;

    public FillFormPresenter(FillFormView listener) {
        EmailApplication.getApplicationComponent().inject(this);
        mListener = listener;
    }

    public void isInputValid(InputData data) {
        try {
            Validator validator = new Validator();
            validator.validInputData(data);
            mListener.onSuccess(data);
        } catch (IllegalArgumentException e) {
            mListener.onError(ErrorMsgFactory.create(context, e));
        }
    }

}
