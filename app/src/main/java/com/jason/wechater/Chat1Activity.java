package com.jason.wechater;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jason.wechater.adapter.Chat1Adapter;
import com.jason.wechater.bean.Msg1Bean;
import com.jason.wechater.bean.MsgBean;
import com.jason.wechater.bean.UserBean;
import com.jason.wechater.manager.UserManager;
import com.jason.wechater.util.DataLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2017/3/27.
 */

public class Chat1Activity extends FragmentActivity{

    public static final String TAG = Chat1Activity.class.getSimpleName();
    private RecyclerView mRvMsgs;
    private Chat1Adapter mAdapter;
    private List<Msg1Bean> mMsgs;
    private UserManager mManager;
    private EditText mEtMeg;
    private TextView mTvTitle;
    private String receiver;
    private UserBean mUserBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat1);
        mRvMsgs = (RecyclerView) findViewById(R.id.chat_rv_msg);
        mEtMeg = (EditText) findViewById(R.id.et_sendmessage);
        mTvTitle = (TextView) findViewById(R.id.title_tv_title);
        mManager = UserManager.getInstance();
        receiver = this.getIntent().getStringExtra("targetAccount");
        initData();
        initView();
    }

    public static void startActivity(Context context,String targetAccount) {
        Intent intent = new Intent(context,Chat1Activity.class);
        intent.putExtra("targetAccount",targetAccount);
        context.startActivity(intent);
    }

    private void initData() {
        mUserBean = DataLoader.getUser(receiver);
        mMsgs = DataLoader.getMsgs(mManager.getLoginBean().getAccount(),receiver);
    }

    private void initView() {
        mTvTitle.setText(mUserBean.get昵称());
        if (mMsgs == null) {
            mMsgs = new ArrayList<>(0);
        }
        mAdapter = new Chat1Adapter(mMsgs);
        mRvMsgs.setAdapter(mAdapter);
        mRvMsgs.setLayoutManager(new LinearLayoutManager(this));
        mEtMeg.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    String msg = mEtMeg.getText().toString();
                    if (msg.length() >0 && !msg.equals("")) {
                        mMsgs.add(new Msg1Bean(msg,mManager.getLoginBean().getAccount()));
                        mAdapter.notifyDataSetChanged();
                        mRvMsgs.smoothScrollToPosition(mMsgs.size()-1);
                        mEtMeg.setText("");
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void onBack(View v) {
        this.finish();
    }
}
