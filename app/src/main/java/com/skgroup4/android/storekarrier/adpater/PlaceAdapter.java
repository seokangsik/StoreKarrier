package com.skgroup4.android.storekarrier.adpater;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skgroup4.android.storekarrier.PlaceActivity;
import com.skgroup4.android.storekarrier.R;
import com.skgroup4.android.storekarrier.item.PlaceItem;

import java.util.ArrayList;

/**
 * Created by Seo on 2017-07-19.
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder>{
    private ArrayList<PlaceItem> placeItemList;

    Context mContext;

    public PlaceAdapter(Context context, ArrayList<PlaceItem> items  ){
        mContext  = context;
        this.placeItemList = items;
    }
    @Override
    public PlaceAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.place_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        PlaceItem item = placeItemList.get(position);
        View.OnClickListener mListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext , PlaceActivity.class);
                mContext.startActivity(intent);
            }
        };
        viewHolder.img.setBackgroundResource(item.img);
        viewHolder.textPrice.setText(item.price);
        viewHolder.textName.setText(item.name);

        viewHolder.layout.setOnClickListener(mListener);
    }


    public int getPixelSize(double input){
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (input * scale + 0.5f);
    }
    @Override
    public int getItemCount() {
        return placeItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public TextView textPrice;
        public TextView textName;
        public LinearLayout layout;
        public ViewHolder(View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.place_img);
            textPrice = (TextView) itemView.findViewById(R.id.place_price_txt);
            textName = (TextView) itemView.findViewById(R.id.place_name_txt);
            layout = (LinearLayout) itemView.findViewById(R.id.place_item_layout);
        }
    }
}
