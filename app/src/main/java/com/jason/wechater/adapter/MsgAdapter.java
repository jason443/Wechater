package com.jason.wechater.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jason.wechater.R;
import com.jason.wechater.bean.ConverBean;
import com.jason.wechater.bean.MsgBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2017/3/15.
 */

public class MsgAdapter extends BaseAdapter {

    private List<ConverBean> mConverBeans;

    public MsgAdapter(List<ConverBean> mConverBeans) {
        this.mConverBeans = mConverBeans;
    }

    @Override
    public int getCount() {
        return mConverBeans.size();
    }

    @Override
    public ConverBean getItem(int position) {
        return mConverBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ConverBean converBean = getItem(position);
        View view;
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_msg1,parent,false);
            viewHolder.mTvName = (TextView) view.findViewById(R.id.msg_tv_name);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.mTvName.setText(converBean.getEquipmentName());
        return view;
    }

    class ViewHolder {
        TextView mTvName;
    }
}
