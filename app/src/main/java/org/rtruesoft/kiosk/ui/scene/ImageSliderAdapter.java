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
    private final int showImageCount = 4;

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
        //int standard = position*5;
        int standard = position*showImageCount;
        if(standard<photos.size()) {
            for(int i=standard; i<standard+showImageCount; i++) {
                switch (i%showImageCount) {
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
        }else {
            int min = photos.size()-standard;
            for(int i=min; i>=0; i--) {
                int mind = photos.size()-i;
                switch (mind%showImageCount) {
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

/*            holder.img1.setImageBitmap(photos.get(standard));
            holder.img2.setImageBitmap(photos.get(standard+1));
            holder.img3.setImageBitmap(photos.get(standard+2));
            holder.img4.setImageBitmap(photos.get(standard+3));
            holder.img5.setImageBitmap(photos.get(standard+4));*/
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if(photos.size()%showImageCount ==0) {
            count = photos.size()/showImageCount;
        }else {
            count = photos.size()/showImageCount + 1 ;
        }
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
            //img5 = itemView.findViewById(R.id.sliderImage5);
        }
    }

}
