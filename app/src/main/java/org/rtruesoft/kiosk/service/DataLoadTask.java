package org.rtruesoft.kiosk.service;

import android.os.AsyncTask;


import org.rtruesoft.kiosk.dto.GetResult;
import org.rtruesoft.kiosk.dto.GetResultDetails;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataLoadTask extends AsyncTask<Void,Void, List<GetResultDetails>> {
    private List<GetResultDetails> resultsDetails;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<GetResultDetails> doInBackground(Void... voids) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/place/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);

        Call<GetResult> call = service.getApiData("seoul+city+point+of+interest","ko","AIzaSyB7SQvBe0JKzGn1KiMSX3N47l7_7Z1hNJw");

        try {
            resultsDetails = call.execute().body().getResults();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultsDetails;
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
