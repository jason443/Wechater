package com.jason.wechater.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jason.wechater.MainActivity;
import com.jason.wechater.R;
import com.jason.wechater.adapter.MsgAdapter;
import com.jason.wechater.bean.ConverBean;
import com.jason.wechater.db.ConverManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2017/3/14.
 */

public class HomeFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener{

    private Activity ctx;
    private View layout;
    public RelativeLayout errorItem;
    public TextView errorText;
    private ListView lvContact;
    private MsgAdapter mAdapter;
    private List<ConverBean> mConverBeans = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (layout == null) {
            ctx = this.getActivity();
            layout = ctx.getLayoutInflater().inflate(R.layout.fragment_home,container,false);
            lvContact = (ListView) layout.findViewById(R.id.listview);
            errorItem = (RelativeLayout) layout
                    .findViewById(R.id.rl_error_item);
            errorText = (TextView) errorItem
                    .findViewById(R.id.tv_connect_errormsg);
            setOnListener();
        } else {
            ViewGroup parent = (ViewGroup) layout.getParent();
            if (parent != null) {
                parent.removeView(layout);
            }
        }
        return layout;
    }

    private void setOnListener() {
        lvContact.setOnItemClickListener(this);
        errorItem.setOnClickListener(this);
    }

    public void addConver(ConverBean converBean) {
        mConverBeans.add(converBean);
        mAdapter.notifyDataSetChanged();
    }

    private void initView() {
        mConverBeans.addAll(ConverManager.getInstance().getConverBean());
        mAdapter = new MsgAdapter(mConverBeans);
        lvContact.setAdapter(mAdapter);
        layout.findViewById(R.id.txt_nochat).setVisibility(View.VISIBLE);
//        if (mConverBeans != null && mConverBeans.size() >0) {
//            mAdapter = new MsgAdapter(mConverBeans);
//            lvContact.setAdapter(mAdapter);
//        } else {
//            layout.findViewById(R.id.txt_nochat).setVisibility(View.VISIBLE);
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public void refresh() {

    }

}
