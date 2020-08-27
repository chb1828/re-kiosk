package org.rtruesoft.kiosk.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.google.gson.Gson;

import org.rtruesoft.kiosk.dto.GetResultDetails;
import org.rtruesoft.kiosk.dto.GetResultPhoto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageLoadTask extends AsyncTask<Void,Void,List<Bitmap>> {
    private List<GetResultDetails> resultsDetails;

    public ImageLoadTask(List<GetResultDetails> resultsDetails) {
        this.resultsDetails = resultsDetails;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Bitmap> doInBackground(Void... voids) {
        Bitmap bitmap = null;
        List<Bitmap> bitmaps = new ArrayList<>();
        for (GetResultDetails grd : resultsDetails) {
            Gson gson = new Gson();
            GetResultPhoto grp = gson.fromJson(gson.toJson(grd.getPhotos().get(0)), GetResultPhoto.class);
            String photoUrl = grp.getPhoto_reference();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://maps.googleapis.com/maps/api/place/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RetrofitService service = retrofit.create(RetrofitService.class);

            Call<ResponseBody> call = service.getApiPhoto(400, photoUrl, "AIzaSyB7SQvBe0JKzGn1KiMSX3N47l7_7Z1hNJw");
            try {
                 bitmap = BitmapFactory.decodeStream(call.execute().body().byteStream());
                 bitmaps.add(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return bitmaps;
    }


}
