package org.rtruesoft.kiosk.ui.scene;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

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

import org.rtruesoft.kiosk.R;
import org.rtruesoft.kiosk.dto.GetResultDetails;
import org.rtruesoft.kiosk.service.DataLoadTask;
import org.rtruesoft.kiosk.service.ImageLoadTask;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutionException;

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

        try {
            DataLoadTask task = new DataLoadTask();
            resultsDetails = (ArrayList<GetResultDetails>) task.execute().get();
            ImageLoadTask task2 = new ImageLoadTask(resultsDetails);
            photos = (ArrayList<Bitmap>) task2.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        imageSliderAdapter = new ImageSliderAdapter(mContext,photos);
        sliderPager.setAdapter(imageSliderAdapter);


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

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        for(GetResultDetails details : resultsDetails) {
            double geometryLat = ((Map<String,Double>)details.getGeometry().get("location")).get("lat");
            double geometryLng = ((Map<String,Double>)details.getGeometry().get("location")).get("lng");
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(geometryLat,geometryLng));
            markerOptions.title(details.getName());
            gMap.addMarker(markerOptions);
        }
        //구글맵의 위치 잡아줌
        MapsInitializer.initialize(this.getActivity());
        LatLng SEOUL = new LatLng(37.56, 126.97);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(14));
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