package org.rtruesoft.kiosk.ui.list;

import android.view.View;
import android.view.ViewStub;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.rtruesoft.kiosk.MainActivity;
import org.rtruesoft.kiosk.R;



public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ExpandableLayout.OnExpansionUpdateListener {
    private static final int UNSELECTED = -1;

    private static int selectedItem = UNSELECTED;
    private RecyclerView recyclerView;
    private ExpandableLayout expandableLayout;
    private TextView expandButton;

    public ViewHolder(View itemView,RecyclerView recyclerView) {
        super(itemView);
        this.recyclerView = recyclerView;
        expandableLayout = itemView.findViewById(R.id.expandable_layout);
        expandableLayout.setInterpolator(new OvershootInterpolator());
        expandableLayout.setOnExpansionUpdateListener(this);
        expandButton = itemView.findViewById(R.id.expand_button);
        expandButton.setOnClickListener(this);
    }

    public void bind() {
        int position = getAdapterPosition();
        boolean isSelected = position == selectedItem;

        ViewStub stub = expandableLayout.findViewById(R.id.menu_stub);
        final MainActivity mainActivity = ((MainActivity)MainActivity.mainContext);
        Button btn1,btn2,btn3;
        View inflated;
        switch (position) {
            case 0 :
                expandButton.setText(itemView.getResources().getString(R.string.menu_scene));
                stub.setLayoutResource(R.layout.scene_item);
                inflated = stub.inflate();
                btn1 = inflated.findViewById(R.id.scene_btn1);
                btn2 = inflated.findViewById(R.id.scene_btn2);
                btn3 = inflated.findViewById(R.id.scene_btn3);

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mainActivity.onFragmentChanged(0);
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mainActivity.onFragmentChanged(1);
                    }
                });
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mainActivity.onFragmentChanged(2);
                    }
                });
                break;
            case 1 :
                expandButton.setText(itemView.getResources().getString(R.string.menu_restaurant));
                stub.setLayoutResource(R.layout.restaurant_item);
                inflated = stub.inflate();
                btn1 = inflated.findViewById(R.id.restaurant_btn1);
                btn2 = inflated.findViewById(R.id.restaurant_btn2);
                btn3 = inflated.findViewById(R.id.restaurant_btn3);

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mainActivity.onFragmentChanged(3);
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mainActivity.onFragmentChanged(4);
                    }
                });
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mainActivity.onFragmentChanged(5);
                    }
                });
                break;
            case 2 :
                expandButton.setText(itemView.getResources().getString(R.string.menu_event));
                stub.setLayoutResource(R.layout.event_item);
                stub.inflate();
                break;
        }
        expandButton.setSelected(isSelected);
        expandableLayout.setExpanded(isSelected, false);

    }

    @Override
    public void onClick(View view) {
        ViewHolder holder = (ViewHolder) recyclerView.findViewHolderForAdapterPosition(selectedItem);
        if (holder != null) {
            holder.expandButton.setSelected(false);
            holder.expandableLayout.collapse();
        }
        int position = getAdapterPosition();
        if (position == selectedItem) {
            selectedItem = UNSELECTED;
        } else {
            expandButton.setSelected(true);
            expandableLayout.expand();
            selectedItem = position;
        }
    }

    @Override
    public void onExpansionUpdate(float expansionFraction, int state) {
        if (state == ExpandableLayout.State.EXPANDING) {
            recyclerView.smoothScrollToPosition(getAdapterPosition());
        }
    }
}