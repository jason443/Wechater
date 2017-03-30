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

import com.jason.wechater.Chat1Activity;
import com.jason.wechater.EquipmentActivity;
import com.jason.wechater.R;
import com.jason.wechater.adapter.MsgAdapter;
import com.jason.wechater.bean.ContactBean;
import com.jason.wechater.bean.ConverBean;
import com.jason.wechater.bean.UserBean;
import com.jason.wechater.manager.ConverManager;
import com.jason.wechater.manager.UserManager;
import com.jason.wechater.util.DataLoader;

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
    private List<UserBean> mContacts;
    private List<ContactBean> mContactList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

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



    private void initView() {
        if (mContacts != null && mContacts.size() >0) {
            mAdapter = new MsgAdapter(mContacts);
            lvContact.setAdapter(mAdapter);
            lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (mContacts.get(position).get类型().equals("人")) {
                        Chat1Activity.startActivity(getContext(),mContactList.get(position).getAccount());
                    } else {
                        EquipmentActivity.startActvity(getContext(),mContacts.get(position));
                    }
                }
            });
        } else {
            layout.findViewById(R.id.txt_nochat).setVisibility(View.VISIBLE);
        }
    }

    private void initData() {
        mContactList = DataLoader.getContactsList(UserManager.getInstance().getLoginBean().getAccount());
        mContacts = DataLoader.getContacts(UserManager.getInstance().getLoginBean().getAccount());
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
