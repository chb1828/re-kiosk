package org.rtruesoft.kiosk.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.net.URL;
import java.util.HashMap;

public class ImageLoadTask extends AsyncTask<Void,Void, Bitmap> {
    private String urlStr;
    private static HashMap<String, Bitmap> bitmapHash = new HashMap<String, Bitmap>();

    public ImageLoadTask(String urlStr) {
        this.urlStr = urlStr;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        Bitmap bitmap = null;
        try {
            if (bitmapHash.containsKey(urlStr)) {
                Bitmap oldbitmap = bitmapHash.remove(urlStr);
                if(oldbitmap != null) {
                    oldbitmap.recycle();
                    oldbitmap = null;
                }
            }
            URL url = new URL(urlStr);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            bitmapHash.put(urlStr,bitmap);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
