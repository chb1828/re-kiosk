package org.rtruesoft.kiosk.ui.scene.photo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;

import org.rtruesoft.kiosk.R;

public class PhotoGridAdapter extends BaseAdapter {

    private Context context;
    private Integer[] posterID;

    public PhotoGridAdapter(Context context) {
        this.context = context;
        posterID = new Integer[]{R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five,
                R.drawable.six,R.drawable.seven,R.drawable.eight,R.drawable.nine,R.drawable.ten,
                R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five,
                R.drawable.six,R.drawable.seven,R.drawable.eight,R.drawable.nine,R.drawable.ten};
    }

    @Override
    public int getCount() {
        return posterID.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new GridView.LayoutParams(200,300));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setPadding(5,5,5,5);
        imageView.setImageResource(posterID[i]);

        final int pos = i;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = View.inflate(context,R.layout.photo_dialog,null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(context);
                ImageView inPoster = dialogView.findViewById(R.id.inPoster);
                inPoster.setImageResource(posterID[pos]);
                dlg.setTitle("포스터 이름");
                dlg.setView(dialogView);
                dlg.setNegativeButton("닫기",null);
                dlg.show();
            }
        });
        return imageView;
    }
}
