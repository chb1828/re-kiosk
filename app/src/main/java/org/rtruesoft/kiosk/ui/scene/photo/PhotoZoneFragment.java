package org.rtruesoft.kiosk.ui.scene.photo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import org.rtruesoft.kiosk.R;

import java.util.ArrayList;

public class PhotoZoneFragment extends Fragment {
    private Context mContext;

    private ListView listView;
    private ArrayList<String> tagList;
    private HashTagAdapter hashTagAdapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_photo_zone, container, false);

        listView = rootView.findViewById(R.id.listView);

        tagList = new ArrayList<>();
        tagList.add("1번");
        tagList.add("2번");
        tagList.add("3번");
        tagList.add("4번");
        tagList.add("5번");

        hashTagAdapter = new HashTagAdapter(mContext,tagList);
        listView.setAdapter(hashTagAdapter);

        GridView gv = rootView.findViewById(R.id.gridView);
        PhotoGridAdapter photoGridAdapter = new PhotoGridAdapter(mContext);
        gv.setAdapter(photoGridAdapter);
        return rootView;
    }
}