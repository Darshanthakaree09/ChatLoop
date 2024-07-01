package com.example.chatloop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatloop.ChatActivity;
import com.example.chatloop.R;
import com.example.chatloop.model.ChatMessageModel;
import com.example.chatloop.utils.AndroidUtil;
import com.example.chatloop.utils.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ChatRecyclerAdapter extends FirestoreRecyclerAdapter<ChatMessageModel,ChatRecyclerAdapter.ChatModelViewHolder>{

    Context context;
    public ChatRecyclerAdapter(@NonNull FirestoreRecyclerOptions<ChatMessageModel> options, Context context) {
        super(options);
        this.context =context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ChatModelViewHolder holder, int position, @NonNull ChatMessageModel model) {
        if(model.getSenderId().equals(FirebaseUtil.currentuserId())){
            holder.leftChatlayout.setVisibility(View.GONE);
            holder.rightChatlayout.setVisibility(View.VISIBLE);
            holder.rightChattextview.setText(model.getMessage());
        }else{
            holder.rightChatlayout.setVisibility(View.GONE);
            holder.leftChatlayout.setVisibility(View.VISIBLE);
            holder.leftChattextview.setText(model.getMessage());
        }
    }

    @NonNull
    @Override
    public ChatModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_message_recycler_row,parent,false);
        return new ChatModelViewHolder(view);
    }

    class ChatModelViewHolder extends RecyclerView.ViewHolder {


        LinearLayout leftChatlayout,rightChatlayout;
        TextView leftChattextview,rightChattextview;
        public ChatModelViewHolder(@NonNull View itemView) {
            super(itemView);

            leftChatlayout = itemView.findViewById(R.id.left_chat_layout);
            rightChatlayout = itemView.findViewById(R.id.right_chat_layout);
            leftChattextview = itemView.findViewById(R.id.left_chat_textview);
            rightChattextview = itemView.findViewById(R.id.right_chat_textview);
        }
    }
}
