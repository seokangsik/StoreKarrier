package com.skgroup4.android.storekarrier.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skgroup4.android.storekarrier.R;
import com.skgroup4.android.storekarrier.adpater.MessageAdapter;
import com.skgroup4.android.storekarrier.item.MessageItem;

import java.util.ArrayList;

public class MessageFragment
        extends Fragment
{
    private RecyclerView.LayoutManager layoutManager;
    private MessageAdapter messageAdapter;
    private ArrayList<MessageItem> messageItemList;
    private RecyclerView messageRecyclerView;
    private TextView showAllMessage;

    private void initData()
    {
        this.messageItemList = new ArrayList();
        this.messageItemList.add(new MessageItem(R.drawable.sender1, "Mike", "OK. I'm in first floor."));
        this.messageItemList.add(new MessageItem(R.drawable.sender2, "Kim", "My address is 59th to 110th Street, Manhattan Borough, from Central Park West to 5th Avenue, New York City, NY 10022."));
        this.messageItemList.add(new MessageItem(R.drawable.sender3, "Ben", "Hi lorna! I'm look forward to going back to New York soon."));
        this.messageItemList.add(new MessageItem(R.drawable.sender4, "Noah", "I will visit soon."));
        this.messageItemList.add(new MessageItem(R.drawable.sender5, "Mason", "I want to meet many people and travel all over the world."));
        this.messageItemList.add(new MessageItem(R.drawable.sender6, "Logan", "I like to travel by train."));
    }

    public void onActivityCreated(@Nullable Bundle paramBundle)
    {
        super.onActivityCreated(paramBundle);
    }

    public void onCreate(@Nullable Bundle Bundle)
    {
        super.onCreate(Bundle);
        initData();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle)
    {
        View view = layoutInflater.inflate(R.layout.fragment_message, viewGroup, false);
        this.messageRecyclerView = ((RecyclerView)view.findViewById(R.id.recommend_message_recycler));
        this.messageRecyclerView.setHasFixedSize(true);
        this.layoutManager = new LinearLayoutManager(getActivity(), 1, false);
        this.messageRecyclerView.setLayoutManager(this.layoutManager);
        this.messageRecyclerView.scrollToPosition(0);
        this.messageAdapter = new MessageAdapter(getActivity(), this.messageItemList);
        this.messageRecyclerView.setAdapter(this.messageAdapter);
        this.messageRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }
}

