package com.skgroup4.android.storekarrier.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skgroup4.android.storekarrier.R;
import com.skgroup4.android.storekarrier.item.MessageItem;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    Context mContext;
    private ArrayList<MessageItem> messageItemList;

    public MessageAdapter(Context paramContext, ArrayList<MessageItem> paramArrayList) {
        this.mContext = paramContext;
        this.messageItemList = paramArrayList;
    }

    public int getItemCount() {
        return this.messageItemList.size();
    }


    public ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ViewHolder(LayoutInflater.from(paramViewGroup.getContext()).inflate(R.layout.message_item, paramViewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewholder, int position) {
        MessageItem localMessageItem = (MessageItem) this.messageItemList.get(position);
        viewholder.img.setBackgroundResource(localMessageItem.img);
        viewholder.textSender.setText(localMessageItem.sender);
        viewholder.textContext.setText(localMessageItem.message);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public LinearLayout layout;
        public TextView textContext;
        public TextView textSender;

        public ViewHolder(View itemView) {
            super(itemView);
            this.img = ((ImageView) itemView.findViewById(R.id.message_img));
            this.textSender = ((TextView) itemView.findViewById(R.id.message_sender_txt));
            this.textContext = ((TextView) itemView.findViewById(R.id.message_content_txt));

        }
    }
}
