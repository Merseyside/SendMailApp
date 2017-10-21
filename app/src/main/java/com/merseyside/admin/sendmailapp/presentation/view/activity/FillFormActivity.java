package com.merseyside.admin.sendmailapp.presentation.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.merseyside.admin.sendmailapp.R;
import com.merseyside.admin.sendmailapp.data.entity.InputData;
import com.merseyside.admin.sendmailapp.presentation.ImageManager;
import com.merseyside.admin.sendmailapp.presentation.presenter.FillFormPresenter;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FillFormActivity extends BaseActivity implements FillFormView{

    @BindView(R.id.photo)
    ImageView photo;

    @BindView(R.id.email)
    TextInputEditText email;

    @BindView(R.id.phone)
    TextInputEditText phone;

    @BindView(R.id.password)
    TextInputEditText password;

    @BindView(R.id.view_button)
    Button viewButton;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    String image_path;
    final String filename = "photo";

    private FillFormPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_form);
        ButterKnife.bind(this);
        mPresenter = new FillFormPresenter(this);
        phone.setText("+74437541056");
        email.setText("john_doe@mail.ru");
        password.setText("qwerty1234_bestpassever");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.view_button)
    public void viewButtonPressed() {
        validateData();
    }

    private void validateData() {
        mPresenter.isInputValid(new InputData(email.getText().toString(), phone.getText().toString(), password.getText().toString(), image_path));
    }

    @OnClick(R.id.photo)
    public void onPhotoPressed() {
        dispatchTakePictureIntent();
    }

    private void dispatchTakePictureIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = new File(Environment.getExternalStorageDirectory(), filename);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(photo));
        image_path = Uri.fromFile(photo).toString();
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            try {
                ImageManager.setPhotoInImageView(this, photo, image_path);
                //photo.setImageURI(Uri.parse(image_path)); // memory leak
            } catch (Exception e) {
                Log.d("tag", "error");
            }
        } else {
            image_path = null;
        }
    }

    @Override
    public void onSuccess(InputData data) {
        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra("data", data);
        startActivity(intent);
    }

    @Override
    public void onError(String msg) {
        showMessage(viewButton, msg);
    }
}
