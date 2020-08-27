package org.rtruesoft.kiosk.ui.list;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.rtruesoft.kiosk.R;

public class MenuAdapter extends RecyclerView.Adapter<ViewHolder>{

    private RecyclerView recyclerView;
    private View itemView;

    public MenuAdapter(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        return new ViewHolder(itemView, recyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind();
    }



    @Override
    public int getItemCount() {
        return 3;
    }

}
