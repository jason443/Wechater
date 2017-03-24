package com.jason.wechater;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.jason.wechater.adapter.ChatAdapter;
import com.jason.wechater.bean.MsgBean;
import com.jason.wechater.widght.DrawView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2017/3/17.
 */

public class ChatActivity extends FragmentActivity {

    private static final String TAG = ChatActivity.class.getSimpleName();
    private String mTitle;
    private DrawView drawView;
    private ListView mList;
    private ChatAdapter mAdapter;
    private EditText mEtMeg;
    private TextView mTvTitle;
    private List<MsgBean> mMsgs = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        findViewById();
        initView();
    }

    public static void startActivity(Context context,String title) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra("Title",title);
        context.startActivity(intent);
    }

    private void initView() {
        mTitle = getIntent().getStringExtra("Title");
        mTvTitle.setText(mTitle);
        mAdapter = new ChatAdapter(mMsgs);
        mEtMeg.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    String msg = mEtMeg.getText().toString();
                    if (msg.length() >0 && !msg.equals("")) {
                        mMsgs.add(new MsgBean(msg,null,MsgBean.TEXT,MsgBean.SEND));
                        mAdapter.refresh();
                        mList.setSelection(mMsgs.size()-1);
                        mEtMeg.setText("");
                    }
                    return true;
                }
                return false;
            }
        });
        mList.setAdapter(mAdapter);
    }

    private void findViewById() {
        drawView = (DrawView) findViewById(R.id.draw_view);
        mList = (ListView) findViewById(R.id.list);
        mEtMeg = (EditText) findViewById(R.id.et_sendmessage);
        mTvTitle = (TextView) findViewById(R.id.title_tv_title);
    }

    public void more(View v) {
        if (drawView.getVisibility() == View.GONE) {
            drawView.setVisibility(View.VISIBLE);
        } else {
            drawView.cleanDraw();
            drawView.setVisibility(View.GONE);
        }
    }

    public void editClick(View v) {

    }

    public void setModeVoice(View v) {

    }

    public void cleanDraw(View v) {
        if (drawView.isClean) {
            drawView.startDraw();
        } else {
            drawView.clear();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
