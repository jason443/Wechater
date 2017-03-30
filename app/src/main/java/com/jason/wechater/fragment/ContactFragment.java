package com.jason.wechater.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jason.wechater.Chat1Activity;
import com.jason.wechater.DetailActivity;
import com.jason.wechater.EquipmentDetailActivity;
import com.jason.wechater.R;
import com.jason.wechater.adapter.ContactAdapter;
import com.jason.wechater.bean.UserBean;
import com.jason.wechater.common.LazyFragment;
import com.jason.wechater.manager.UserManager;
import com.jason.wechater.util.DataLoader;

import java.util.List;

/**
 * Created by jason on 2017/3/14.
 */

public class ContactFragment extends Fragment {

    private static final String TAG = ContactFragment.class.getSimpleName();
    private RecyclerView mRvContactList;
    private List<UserBean> mContacts;
    private ContactAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact,container,false);
        mRvContactList = (RecyclerView) view.findViewById(R.id.contact_rv_friends);
        initView();
        return view;
    }

    private void initView() {
        mContacts = DataLoader.getContacts(UserManager.getInstance().getLoginBean().getAccount());
        mAdapter = new ContactAdapter(mContacts);
        mAdapter.setmListener(new ContactAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (mContacts.get(position).get类型().equals("人")) {
                    DetailActivity.startActivity(getContext(),mContacts.get(position));
                } else {
                    EquipmentDetailActivity.startActivity(getContext(),mContacts.get(position));
                }
            }
        });
        mRvContactList.setAdapter(mAdapter);
        mRvContactList.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
