package com.merseyside.admin.sendmailapp.presentation.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.merseyside.admin.sendmailapp.R;
import com.merseyside.admin.sendmailapp.data.entity.InputData;
import com.merseyside.admin.sendmailapp.presentation.ImageManager;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ivan_ on 20.10.2017.
 */

public class ViewActivity extends BaseActivity {
    private InputData data;

    @BindView(R.id.photo)
    ImageView photo;

    @BindView(R.id.email)
    TextView email;

    @BindView(R.id.phone)
    TextView phone;

    @BindView(R.id.password)
    TextView password;

    @BindView(R.id.send)
    Button send;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ButterKnife.bind(this);
        data = getIntent().getExtras().getParcelable("data");
        fillView(data);
    }

    @OnClick(R.id.send)
    public void onSendButtonClicked(){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto",data.getEmail(), null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name));
        emailIntent.putExtra(Intent.EXTRA_TEXT, data.getEmail());
        emailIntent.putExtra(Intent.EXTRA_TEXT, data.getPhone());
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(data.getPhotoPath()));
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void fillView(InputData data) {
        email.setText(data.getEmail());
        phone.setText(data.getPhone());
        password.setText(data.getPassword());
        ImageManager.setPhotoInImageView(this, photo, data.getPhotoPath());
        //photo.setImageURI(Uri.parse(data.getPhotoPath())); // memory leak
    }
}
