package com.merseyside.admin.sendmailapp.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by ivan_ on 20.10.2017.
 */

public class ImageManager {

    public static void setPhotoInImageView(final Context context, final ImageView iv, final String path) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ViewTreeObserver vto = iv.getViewTreeObserver();
                vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        float finalHeight = iv.getMeasuredHeight();
                        float finalWidth = iv.getMeasuredWidth();
                        byte[] data = new byte[0];
                        try {
                            data = getDataByPath(path);
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                        final Bitmap bmp = decodeSampledBitmapFromData(data, (int)finalWidth, (int)finalHeight, data.length);
                        Activity activity = (Activity) context;
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                iv.setImageBitmap(bmp);
                            }
                        });
                        iv.getViewTreeObserver().removeOnPreDrawListener(this);
                        iv.getViewTreeObserver().removeOnPreDrawListener(this);
                        return true;
                    }
                });
            }
        }).start();

    }

    private static byte[] getDataByPath(String path) throws URISyntaxException {
        File file = new File(new URI(path));
        int size = (int) file.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);
            buf.close();
            return bytes;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static Bitmap decodeSampledBitmapFromData(byte[] res, int width, int height, int lenght) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(res, 0, lenght, options);
        options.inSampleSize = calculateInSampleSize(options, width, height);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(res, 0,  lenght, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight){
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
