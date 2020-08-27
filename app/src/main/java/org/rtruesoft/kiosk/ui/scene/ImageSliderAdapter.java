package org.rtruesoft.kiosk.ui.scene;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import org.rtruesoft.kiosk.R;
import java.util.List;



public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder> {

    private Context context;
    private List<Bitmap> photos;

    public ImageSliderAdapter(Context context,List<Bitmap> photos) {
        this.context = context;
        this.photos = photos;
    }

    @NonNull
    @Override
    public ImageSliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.slider_item,parent,false);
        return new ImageSliderViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSliderViewHolder holder, int position) {
        System.out.println("뷰홀더 실행됨");
        int standard = position*5;
        holder.img1.setImageBitmap(photos.get(standard));
        holder.img2.setImageBitmap(photos.get(standard+1));
        holder.img3.setImageBitmap(photos.get(standard+2));
        holder.img4.setImageBitmap(photos.get(standard+3));
        holder.img5.setImageBitmap(photos.get(standard+4));
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if(photos.size()%5 ==0) {
            count = photos.size()/5;
        }else {
            count = photos.size()/5 + 1 ;
        }
        System.out.println(count+"개수");
        return count;   // 들어온 개수 / 5 ;
    }


    public class ImageSliderViewHolder extends RecyclerView.ViewHolder {
        ImageView img1,img2,img3,img4,img5;

        public ImageSliderViewHolder(@NonNull View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.sliderImage);
            img2 = itemView.findViewById(R.id.sliderImage2);
            img3 = itemView.findViewById(R.id.sliderImage3);
            img4 = itemView.findViewById(R.id.sliderImage4);
            img5 = itemView.findViewById(R.id.sliderImage5);
        }
    }

}
