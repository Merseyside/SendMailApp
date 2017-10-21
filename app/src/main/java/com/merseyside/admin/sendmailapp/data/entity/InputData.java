package com.merseyside.admin.sendmailapp.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ivan_ on 20.10.2017.
 */

public class InputData implements Parcelable{
    String email;
    String phone;
    String password;
    String photo_path;

    protected InputData(Parcel in) {
        email = in.readString();
        phone = in.readString();
        password = in.readString();
        photo_path = in.readString();
    }

    public static final Creator<InputData> CREATOR = new Creator<InputData>() {
        @Override
        public InputData createFromParcel(Parcel in) {
            return new InputData(in);
        }

        @Override
        public InputData[] newArray(int size) {
            return new InputData[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getPhotoPath() {
        return photo_path;
    }

    public InputData(String email, String phone, String password, String photo_path) {
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.photo_path = photo_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(email);
        parcel.writeString(phone);
        parcel.writeString(password);
        parcel.writeString(photo_path);
    }
}
