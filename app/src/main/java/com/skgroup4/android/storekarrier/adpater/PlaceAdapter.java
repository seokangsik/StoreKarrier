package com.skgroup4.android.storekarrier.adpater;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skgroup4.android.storekarrier.PlaceActivity;
import com.skgroup4.android.storekarrier.R;
import com.skgroup4.android.storekarrier.item.PlaceItem;
import com.skgroup4.android.storekarrier.item.RepoSpot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.skgroup4.android.storekarrier.adpater.StoreAdapter.RECOMMEND_CODE;

/**
 * Created by Seo on 2017-07-19.
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder>{
    private ArrayList<PlaceItem> placeItemList;
    private ArrayList<RepoSpot> spotList;
    private boolean dataChecker;

    public static final int PLACE_CODE = 10003;
    private static int code;
    Context mContext;
    public PlaceAdapter(Context context, ArrayList<PlaceItem> items ,int code  ){
        mContext  = context;
        this.placeItemList = items;
        this.code = code;
    }
    public PlaceAdapter(Context context, ArrayList<RepoSpot> items , int code, boolean checker){
        mContext = context;
        this.spotList = items;
        this.code = code;
        dataChecker = checker;
    }
    @Override
    public PlaceAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.place_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final int itemPosition = position;
        if(dataChecker){
            RepoSpot item = spotList.get(itemPosition);
            Picasso.with(mContext).load(item.getPlaceImg()).fit().centerCrop().into(viewHolder.img);
            //viewHolder.img.setBackgroundResource(item.getPlaceImg());
            viewHolder.textName.setText(item.getPlaceName());
        }
        else{
            PlaceItem item = placeItemList.get(position);
            viewHolder.img.setBackgroundResource(item.img);
            viewHolder.textName.setText(item.name);
        }

        View.OnClickListener mListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext , PlaceActivity.class);
                if(spotList!=null){
                    RepoSpot item = spotList.get(itemPosition);
                    intent.putExtra("spotInfo" , item);
                }
                mContext.startActivity(intent);
            }
        };


        viewHolder.layout.setOnClickListener(mListener);
    }


    public int getPixelSize(double input){
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (input * scale + 0.5f);
    }

    @Override
    public int getItemCount() {
        if(dataChecker){
            return spotList.size();
        }else{
            return placeItemList.size();
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public TextView textName;
        public LinearLayout layout;
        public ViewHolder(View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.place_img);
            textName = (TextView) itemView.findViewById(R.id.place_name_txt);
            layout = (LinearLayout) itemView.findViewById(R.id.place_item_layout);

            if(code == RECOMMEND_CODE){
                setMargins(layout , 0 , 0 , 50 , 0);
            }
            else if (code == PLACE_CODE){
                float margin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, itemView.getResources().getDisplayMetrics());
                setMargins(layout , (int)margin , 0 , (int)margin , 0);
                float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, itemView.getResources().getDisplayMetrics());
                img.setLayoutParams(new LinearLayout.LayoutParams((int) pixels, (int)pixels));
            }
        }
    }
    public static void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }
}
