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

import com.skgroup4.android.storekarrier.R;
import com.skgroup4.android.storekarrier.StoreActivity;
import com.skgroup4.android.storekarrier.item.StoreItem;

import java.util.ArrayList;

/**
 * Created by Seo on 2017-07-19.
 */

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {

    private ArrayList<StoreItem> storeItemList;
    public static final int RECOMMEND_CODE = 10001;
    public static final int STORE_CODE = 10002;

    private static int code;

    Context mContext;

    public StoreAdapter(Context context , ArrayList<StoreItem> items , int  code){
        mContext = context;
        this.storeItemList = items;
        this.code = code;
    }
    @Override
    public StoreAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.store_item,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StoreAdapter.ViewHolder viewHolder, int position) {
        StoreItem item = storeItemList.get(position);
        View.OnClickListener mListener = new  View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, StoreActivity.class);
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
        return storeItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public TextView textPrice;
        public TextView textName;
        public LinearLayout layout;
        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.store_img);
            textPrice = (TextView) itemView.findViewById(R.id.store_price_txt);
            textName = (TextView) itemView.findViewById(R.id.store_name_txt);
            layout = (LinearLayout) itemView.findViewById(R.id.store_item_layout);
            if(code == RECOMMEND_CODE){
                //img.setLayoutParams(new LinearLayout.LayoutParams(200,150));
            }
            else if (code == STORE_CODE){
                layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                img.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,700));
            }
        }
    }
}

