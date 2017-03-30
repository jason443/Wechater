package com.jason.wechater.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jason.wechater.R;
import com.jason.wechater.bean.MsgBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2017/3/16.
 */

public class ChatAdapter extends BaseAdapter{
    
    public static final String TAG = ChatAdapter.class.getSimpleName();
    public static final int TYPE_TEXT = 0;
    public static final int TYPE_PHOTO = 1;
    public static final int TYPE_SEND = 2;
    public static final int TYPE_RECEVIED = 3;
    private List<MsgBean> mMsgs = new ArrayList<>();

    public ChatAdapter(List<MsgBean> mMsgs) {
        this.mMsgs = mMsgs;
    }

    @Override
    public int getCount() {
        return mMsgs.size();
    }

    @Override
    public MsgBean getItem(int position) {
        return mMsgs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MsgBean msg = getItem(position);
        View view;
        if (getItemViewType(position) == TYPE_TEXT) {
            TextViewHolder viewHolder;
            if (convertView == null || convertView.getTag() instanceof PhotoViewHolder) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_msg,parent,false);
                viewHolder = new TextViewHolder();
                viewHolder.leftMsg = (TextView) view.findViewById(R.id.left_msg);
                viewHolder.rightMsg = (TextView) view.findViewById(R.id.right_msg);
                view.setTag(viewHolder);
            } else {
                view = convertView;
                viewHolder = (TextViewHolder) view.getTag();
            }
            if (msg.getType2() == TYPE_SEND) {
                viewHolder.rightMsg.setText(msg.getMsg());
                viewHolder.leftMsg.setVisibility(View.GONE);
            }else {
                viewHolder.leftMsg.setText(msg.getMsg());
                viewHolder.rightMsg.setVisibility(View.GONE);
            }
        }else {
            PhotoViewHolder viewHolder;
            if (convertView == null || convertView.getTag() instanceof TextViewHolder) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo,parent,false);
                viewHolder = new PhotoViewHolder();
                viewHolder.leftPhoto = (ImageView) view.findViewById(R.id.left_photo);
                viewHolder.rightPhoto = (ImageView) view.findViewById(R.id.right_photo);
                view.setTag(viewHolder);
            }else {
                view = convertView;
                viewHolder = (PhotoViewHolder) view.getTag();
            }
            if (msg.getType2() == TYPE_SEND) {
                viewHolder.rightPhoto.setImageBitmap(msg.getPhoto());
           //     viewHolder.leftPhoto.setVisibility(View.GONE);
            } else {
                viewHolder.rightPhoto.setImageBitmap(msg.getPhoto());
        //        viewHolder.leftPhoto.setVisibility(View.GONE);
            }
        }
        return view;
    }

    @Override
    public int getItemViewType(int position) {
        return mMsgs.get(position).getType1();
    }

    class TextViewHolder {
        TextView leftMsg,rightMsg;
    }

    class PhotoViewHolder {
        ImageView leftPhoto,rightPhoto;
    }

    public void refresh() {
        Log.d(TAG, "refresh: ");
        notifyDataSetChanged();
    }
}
