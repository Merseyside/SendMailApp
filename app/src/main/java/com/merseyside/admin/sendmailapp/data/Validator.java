package com.merseyside.admin.sendmailapp.data;

import android.content.Context;
import android.net.Uri;

import com.merseyside.admin.sendmailapp.R;
import com.merseyside.admin.sendmailapp.data.entity.InputData;
import com.merseyside.admin.sendmailapp.presentation.EmailApplication;

import java.util.IllegalFormatException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

/**
 * Created by ivan_ on 20.10.2017.
 */

public class Validator {

    @Inject
    Context context;

    public Validator() {
        EmailApplication.getApplicationComponent().inject(this);
    }

    static final String email_pattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    static final String phone_pattern = "^\\+[0-9]{11}$";
    static final String password_pattern = "^(?=.*[0-9])(?=.*[A-Z]).{6,}$";

    public void validInputData(InputData data) throws IllegalArgumentException {
        validEmail(data.getEmail());
        validPhone(data.getPhone());
        validPassword(data.getPassword());
        validPhoto(data.getPhotoPath());
    }

    private void validEmail(String email) throws IllegalArgumentException{
        Pattern pattern = Pattern.compile(email_pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.find()) {
            throw new IllegalArgumentException(context.getString(R.string.wrong_email));
        }
    }

    private void validPhone(String phone) throws IllegalArgumentException{
        Pattern pattern = Pattern.compile(phone_pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.find()) {
            throw new IllegalArgumentException(context.getString(R.string.wrong_phone));
        }
    }

    private void validPassword(String phone) throws IllegalArgumentException{
        Pattern pattern = Pattern.compile(password_pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.find()) {
            throw new IllegalArgumentException(context.getString(R.string.wrong_password));
        }
    }

    private void validPhoto(String photo) throws IllegalArgumentException{
        try {
            Uri.parse(photo);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(context.getString(R.string.wrong_photo));
        }
    }
}
