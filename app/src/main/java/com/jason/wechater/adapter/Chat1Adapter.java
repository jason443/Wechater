package com.jason.wechater.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jason.wechater.R;
import com.jason.wechater.bean.Msg1Bean;
import com.jason.wechater.manager.UserManager;

import java.util.List;

/**
 * Created by jason on 2017/3/27.
 */

public class Chat1Adapter extends RecyclerView.Adapter<Chat1Adapter.ChatViewHolder>{

    private List<Msg1Bean> mMsg;
    private UserManager mManager;

    public Chat1Adapter(List<Msg1Bean> mMsg) {
        this.mMsg = mMsg;
        mManager = UserManager.getInstance();
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChatViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_msg,parent,false));
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        if (mMsg.get(position).getMaster().equals(mManager.getLoginBean().getAccount())) {
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightMsg.setText(mMsg.get(position).getContent());
        } else {
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMsg.setText(mMsg.get(position).getContent());
        }
    }

    @Override
    public int getItemCount() {
        return mMsg==null?0:mMsg.size();
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {

        View leftLayout;
        View rightLayout;
        ImageView leftAvatar;
        ImageView rightAvatar;
        TextView leftMsg;
        TextView rightMsg;

        public ChatViewHolder(View itemView) {
            super(itemView);
            leftLayout = itemView.findViewById(R.id.left_layout);
            rightLayout = itemView.findViewById(R.id.right_layout);
            leftAvatar = (ImageView) itemView.findViewById(R.id.left_avatar);
            rightAvatar = (ImageView) itemView.findViewById(R.id.right_avatar);
            leftMsg = (TextView) itemView.findViewById(R.id.left_msg);
            rightMsg = (TextView) itemView.findViewById(R.id.right_msg);
        }
    }

}

