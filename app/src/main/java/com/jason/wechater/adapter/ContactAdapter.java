package com.jason.wechater.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jason.wechater.R;
import com.jason.wechater.bean.UserBean;

import java.util.List;

/**
 * Created by jason on 2017/3/26.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{

    private List<UserBean> mContacts;

    public ContactAdapter(List<UserBean> mContacts) {
        this.mContacts = mContacts;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_friend,parent,false));
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.mTvName.setText(mContacts.get(position).get昵称());
    }

    @Override
    public int getItemCount() {
        return mContacts == null?0:mContacts.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder{
        TextView mTvName;
        ImageView mIvAvatar;

        public ContactViewHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.contact_tv_name);
            mIvAvatar = (ImageView) itemView.findViewById(R.id.contact_iv_avatar);
        }
    }
}
