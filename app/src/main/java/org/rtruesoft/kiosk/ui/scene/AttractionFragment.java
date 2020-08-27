package org.rtruesoft.kiosk.ui.scene;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import org.rtruesoft.kiosk.R;
import org.rtruesoft.kiosk.dto.GetResult;
import org.rtruesoft.kiosk.dto.GetResultDetails;
import org.rtruesoft.kiosk.dto.GetResultPhoto;
import org.rtruesoft.kiosk.service.ImageLoadTask;
import org.rtruesoft.kiosk.service.RetrofitService;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AttractionFragment extends Fragment implements OnMapReadyCallback {

    private MapView mapView;
    private ArrayList<GetResultDetails> resultsDetails;
    private ViewPager2 sliderPager;
    private ImageSliderAdapter imageSliderAdapter;
    private Context mContext;
    private ArrayList<Bitmap> photos;
    private GoogleMap gMap;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        photos = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_attraction, container, false);
        mapView = rootView.findViewById(R.id.map_View);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);


        sliderPager = rootView.findViewById(R.id.photoViewPager);
        sliderPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        sliderPager.setOffscreenPageLimit(3);

/*        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(()-> {
            getMapData(); //맵 api 데이터 통신
            getPhotoBitmap(); //장소 api 데이터 통신
            imageSliderAdapter = new ImageSliderAdapter(mContext,photos);
            sliderPager.setAdapter(imageSliderAdapter);
        });

        es.shutdown();*/



        final float pageMargin= 30.0f;
        final float pageOffset = 30.0f;
        sliderPager.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float myOffset = position * -(2 * pageOffset + pageMargin);
                if (sliderPager.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {
                    if (ViewCompat.getLayoutDirection(sliderPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                        page.setTranslationX(-myOffset);
                    } else {
                        page.setTranslationX(myOffset);
                    }
                } else {
                    page.setTranslationY(myOffset);
                }
            }
        });
        getMapData();


        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        //구글맵의 위치 잡아줌
        MapsInitializer.initialize(this.getActivity());
        LatLng SEOUL = new LatLng(37.56, 126.97);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(14));
    }

    public void getMapData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/place/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);

        Call<GetResult> call = service.getApiData("seoul+city+point+of+interest","ko","AIzaSyB7SQvBe0JKzGn1KiMSX3N47l7_7Z1hNJw");

        call.enqueue(new Callback<GetResult>() {
            @Override
            public void onResponse(Call<GetResult> call, Response<GetResult> response) {
                if(response.isSuccessful()) {
                    resultsDetails = response.body().getResults();
                    getPhotoBitmap();
                    for(GetResultDetails details : resultsDetails) {
                        double geometryLat = ((Map<String,Double>)details.getGeometry().get("location")).get("lat");
                        double geometryLng = ((Map<String,Double>)details.getGeometry().get("location")).get("lng");
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(new LatLng(geometryLat,geometryLng));
                        markerOptions.title(details.getName());
                        gMap.addMarker(markerOptions);
                    }
                }else{
                    Log.d("테스트","실패" + response.message());
                }
            }

            @Override
            public void onFailure(Call<GetResult> call, Throwable t) {
                Log.d("테스트","완전 실패"+t.getMessage());
            }
        });
        //return resultsDetails;
    }

    public void getPhotoBitmap() {
         for(GetResultDetails grd : resultsDetails) {
            Gson gson = new Gson();
            GetResultPhoto grp = gson.fromJson(gson.toJson(grd.getPhotos().get(0)), GetResultPhoto.class);
            String photoUrl = grp.getPhoto_reference();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://maps.googleapis.com/maps/api/place/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RetrofitService service = retrofit.create(RetrofitService.class);
            service.getApiPhoto(400,photoUrl,"AIzaSyB7SQvBe0JKzGn1KiMSX3N47l7_7Z1hNJw")
                    .enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            String url = response.raw().request().url().toString();
                            ImageLoadTask task = new ImageLoadTask(url);
                            try {
                                Bitmap bitmap = task.execute().get();
                                photos.add(bitmap);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            System.out.println("실패"+t.getMessage());
                        }
                    });
        }
        imageSliderAdapter = new ImageSliderAdapter(mContext,photos);
        sliderPager.setAdapter(imageSliderAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}