package com.eselman.medisys.adapters;

import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eselman.medisys.R;

/**
 * Created by Evangelina Selman on 28/01/2017.
 */
public class DrawerMenuAdapter extends RecyclerView.Adapter<DrawerMenuAdapter.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private String menuOptionLabels[];
    private TypedArray icons;

    // Creating a ViewHolder which extends the RecyclerView View Holder
    // ViewHolder are used to to store the inflated views in order to recycle them
    public static class ViewHolder extends RecyclerView.ViewHolder {
        int holderId;
        TextView textView;
        ImageView imageView;

        // Creating ViewHolder Constructor with View and ViewType as a parameter
        public ViewHolder(View itemView, int ViewType) {
            super(itemView);

            if (ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.rowText);
                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                holderId = 1;
            } else {
                holderId = 0;
            }
        }
    }

    public DrawerMenuAdapter(String menuOptionLabels[], TypedArray icons) {
        this.menuOptionLabels = menuOptionLabels;
        this.icons = icons;
    }

    @Override
    public DrawerMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_list_item, parent, false);
            ViewHolder vhItem = new ViewHolder(v, viewType);
            return vhItem;
        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false);
            ViewHolder vhHeader = new ViewHolder(v, viewType);
            return vhHeader;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(DrawerMenuAdapter.ViewHolder holder, int position) {
        if (holder.holderId == 1) {
            // position by 1 and pass it to the holder while setting the text and image
            holder.textView.setText(menuOptionLabels[position - 1]);
            holder.imageView.setImageResource(icons.getResourceId(position-1, -1));
        }
    }

    @Override
    public int getItemCount() {
        return menuOptionLabels.length + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }
}
