package org.rtruesoft.kiosk.ui.scene;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import org.rtruesoft.kiosk.R;
import org.rtruesoft.kiosk.dto.GetResultDetails;
import org.rtruesoft.kiosk.service.ImageLoadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder> {

    private Context context;
    private List<Bitmap> photos;
    private ArrayList<GetResultDetails> resultsDetails;
    private GoogleMap googleMap;
    private final int showImageCount = 4;

    public ImageSliderAdapter(Context context, ArrayList<GetResultDetails> resultsDetails, GoogleMap googleMap) throws ExecutionException, InterruptedException {
        this.context = context;
        this.resultsDetails = resultsDetails;
        this.googleMap = googleMap;

        ImageLoadTask task2 = new ImageLoadTask(resultsDetails);
        photos = (ArrayList<Bitmap>) task2.execute().get();

    }

    @NonNull
    @Override
    public ImageSliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.slider_item, parent, false);
        return new ImageSliderViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSliderViewHolder holder, int position) {
        int standard = position * showImageCount;
        setImage(standard,holder);
        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double geometryLat = ((Map<String,Double>)resultsDetails.get(standard).getGeometry().get("location")).get("lat");
                double geometryLng = ((Map<String,Double>)resultsDetails.get(standard).getGeometry().get("location")).get("lng");
                LatLng latlng = new LatLng(geometryLat, geometryLng);
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(17));
            }
        });
        holder.img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double geometryLat = ((Map<String,Double>)resultsDetails.get(standard+1).getGeometry().get("location")).get("lat");
                double geometryLng = ((Map<String,Double>)resultsDetails.get(standard+1).getGeometry().get("location")).get("lng");
                LatLng latlng = new LatLng(geometryLat, geometryLng);
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(17));
            }
        });
        holder.img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double geometryLat = ((Map<String,Double>)resultsDetails.get(standard+2).getGeometry().get("location")).get("lat");
                double geometryLng = ((Map<String,Double>)resultsDetails.get(standard+2).getGeometry().get("location")).get("lng");
                LatLng latlng = new LatLng(geometryLat, geometryLng);
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(17));
            }
        });
        holder.img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double geometryLat = ((Map<String,Double>)resultsDetails.get(standard+3).getGeometry().get("location")).get("lat");
                double geometryLng = ((Map<String,Double>)resultsDetails.get(standard+3).getGeometry().get("location")).get("lng");
                LatLng latlng = new LatLng(geometryLat, geometryLng);
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(17));
            }
        });
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (photos.size() % showImageCount == 0) {
            count = photos.size() / showImageCount;
        } else {
            count = photos.size() / showImageCount + 1;
        }
        return count;   // 들어온 개수 / 5 ;
    }

    private void setImage(int standard,ImageSliderViewHolder holder) {
        if (standard < photos.size()) {
            for (int i = standard; i < standard + showImageCount; i++) {
                switch (i % showImageCount) {
                    case 0:
                        holder.img1.setImageBitmap(photos.get(i));
                        break;
                    case 1:
                        holder.img2.setImageBitmap(photos.get(i));
                        break;
                    case 2:
                        holder.img3.setImageBitmap(photos.get(i));
                        break;
                    case 3:
                        holder.img4.setImageBitmap(photos.get(i));
                        break;
/*                    case 4:
                        holder.img5.setImageBitmap(photos.get(i));
                        break;*/
                }
            }
        } else {
            int min = photos.size() - standard;
            for (int i = min; i >= 0; i--) {
                int mind = photos.size() - i;
                switch (mind % showImageCount) {
                    case 0:
                        holder.img1.setImageBitmap(photos.get(i));
                        break;
                    case 1:
                        holder.img2.setImageBitmap(photos.get(i));
                        break;
                    case 2:
                        holder.img3.setImageBitmap(photos.get(i));
                        break;
/*                    case 3:
                        holder.img4.setImageBitmap(photos.get(i));
                        break;*/
                }
            }
        }
    }


    public class ImageSliderViewHolder extends RecyclerView.ViewHolder {
        ImageView img1, img2, img3, img4, img5;

        public ImageSliderViewHolder(@NonNull View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.sliderImage);
            img2 = itemView.findViewById(R.id.sliderImage2);
            img3 = itemView.findViewById(R.id.sliderImage3);
            img4 = itemView.findViewById(R.id.sliderImage4);
            //img5 = itemView.findViewById(R.id.sliderImage5);


        }
    }

}
