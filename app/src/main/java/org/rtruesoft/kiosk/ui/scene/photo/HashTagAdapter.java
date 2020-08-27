package org.rtruesoft.kiosk.ui.scene.photo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.rtruesoft.kiosk.R;

import java.util.ArrayList;

public class HashTagAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> hashTag;

    private ViewHolder mViewHolder;

    public HashTagAdapter(Context context, ArrayList<String> hashTag) {
        this.context = context;
        this.hashTag = hashTag;
    }

    @Override
    public int getCount() {
        return hashTag.size();
    }

    @Override
    public Object getItem(int i) {
        return hashTag.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view ==null) {
            view = LayoutInflater.from(context).inflate(R.layout.hash_tag_item,viewGroup,false);
            mViewHolder = new ViewHolder(view);
            view.setTag(mViewHolder);
        }else {
            mViewHolder = (ViewHolder) view.getTag();
        }
        mViewHolder.txt_name.setText(hashTag.get(i));
        return view;
    }
    public class ViewHolder {

        private TextView txt_name;

        public ViewHolder(View convertView) {

            txt_name = (TextView) convertView.findViewById(R.id.tag_name);

        }

    }
}
