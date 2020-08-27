package org.rtruesoft.kiosk.service;

import org.rtruesoft.kiosk.dto.GetResult;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("textsearch/json?")
    Call<GetResult> getApiData(@Query("query") String query,@Query("language") String language, @Query("key") String key);

    @GET("photo?")
    Call<ResponseBody> getApiPhoto(@Query("maxwidth") int maxwidth, @Query("photoreference") String photoReference, @Query("key") String key);
}
