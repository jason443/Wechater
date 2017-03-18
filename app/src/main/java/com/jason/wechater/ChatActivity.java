package com.jason.wechater;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.jason.wechater.adapter.ChatAdapter;
import com.jason.wechater.bean.MsgBean;
import com.jason.wechater.widght.DrawView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2017/3/16.
 */

public class ChatActivity extends FragmentActivity implements View.OnClickListener{

    private static final String TAG = ChatActivity.class.getSimpleName();
    private LinearLayout more;
    private LinearLayout draw;
    private DrawView drawView;
    private InputMethodManager manager;
    public static final String COPY_IMAGE = "EASEMOBIMG";
    public static final int REQUEST_CODE_COPY_AND_PASTE = 11;
    private LinearLayout btnContainer;
    private LinearLayout emojiIconContainer;
    private ImageView iv_emoticons_normal;
    private ImageView iv_emoticons_checked;
    private ImageView mIvLeft,mIvRight;
    private Button btnMore;
    private ChatAdapter mAdapter;
    private ListView mList;
    private List<MsgBean> mMsgs = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        findViewById();
        initView();
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ChatActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.title_iv_left:
                this.finish();
                break;
            case R.id.title_iv_right:
                break;
            case R.id.iv_emoticons_normal:
                // 点击显示表情框
                more.setVisibility(View.VISIBLE);
                iv_emoticons_normal.setVisibility(View.INVISIBLE);
                iv_emoticons_checked.setVisibility(View.VISIBLE);
                btnContainer.setVisibility(View.GONE);
                emojiIconContainer.setVisibility(View.VISIBLE);
                hideKeyboard();
                break;
            case R.id.iv_emoticons_checked:// 点击隐藏表情框
                iv_emoticons_normal.setVisibility(View.VISIBLE);
                iv_emoticons_checked.setVisibility(View.INVISIBLE);
                btnContainer.setVisibility(View.VISIBLE);
                emojiIconContainer.setVisibility(View.GONE);
                more.setVisibility(View.GONE);
                break;
        }
    }

    private void initView() {
        mIvLeft.setOnClickListener(this);
        mIvRight.setOnClickListener(this);
        iv_emoticons_normal.setOnClickListener(this);
        iv_emoticons_checked.setOnClickListener(this);
        mAdapter = new ChatAdapter(mMsgs);
        mList.setAdapter(mAdapter);
    }

    private void findViewById() {
        mIvLeft = (ImageView) findViewById(R.id.title_iv_left);
        mIvRight = (ImageView) findViewById(R.id.title_iv_right);
        iv_emoticons_normal = (ImageView) findViewById(R.id.iv_emoticons_normal);
        iv_emoticons_checked = (ImageView) findViewById(R.id.iv_emoticons_checked);
        iv_emoticons_normal.setVisibility(View.VISIBLE);
        iv_emoticons_checked.setVisibility(View.INVISIBLE);
        emojiIconContainer = (LinearLayout) findViewById(R.id.ll_face_container);
        btnContainer = (LinearLayout) findViewById(R.id.ll_btn_container);
        more = (LinearLayout) findViewById(R.id.more);
        btnMore = (Button) findViewById(R.id.btn_more);
        draw = (LinearLayout) findViewById(R.id.draw);
        mList = (ListView) findViewById(R.id.list);
        drawView = (DrawView) findViewById(R.id.draw_view);
    }

    public void setModeVoice(View v) {
        if (draw.getVisibility() == View.GONE) {
            draw.setVisibility(View.VISIBLE);
            drawView.requestFocus();
        } else {
            draw.setVisibility(View.GONE);
        }
    }

    public void editClick(View view) {
        Toast.makeText(this,"111",Toast.LENGTH_SHORT).show();
        if (more.getVisibility() == View.VISIBLE) {
            more.setVisibility(View.GONE);
            iv_emoticons_normal.setVisibility(View.VISIBLE);
            iv_emoticons_checked.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 显示或隐藏图标按钮页
     *
     * @param view
     */
    public void more(View view) {
        if (more.getVisibility() == View.GONE) {
            System.out.println("more gone");
            hideKeyboard();
            more.setVisibility(View.VISIBLE);
            btnContainer.setVisibility(View.VISIBLE);
            emojiIconContainer.setVisibility(View.GONE);
        } else {
            if (emojiIconContainer.getVisibility() == View.VISIBLE) {
                emojiIconContainer.setVisibility(View.GONE);
                btnContainer.setVisibility(View.VISIBLE);
                iv_emoticons_normal.setVisibility(View.VISIBLE);
                iv_emoticons_checked.setVisibility(View.INVISIBLE);
            } else {
                more.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 隐藏软键盘
     */
    private void hideKeyboard() {
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
