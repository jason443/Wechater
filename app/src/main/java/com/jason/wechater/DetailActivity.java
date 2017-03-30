package com.jason.wechater;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jason.wechater.bean.UserBean;

/**
 * Created by jason on 2017/3/30.
 */

public class DetailActivity extends FragmentActivity{

    private TextView mTvTitle;
    private TextView mTvName;
    private TextView mTvGender;
    private TextView mTvAge;
    private TextView mTvPhone;
    private TextView mTvBirthday;
    private ImageView mIvAvatar;
    private UserBean mUserBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = this.getIntent();
        mUserBean = new UserBean();
        mUserBean.set年龄(intent.getStringExtra("年龄"));
        mUserBean.set性别(intent.getStringExtra("性别"));
        mUserBean.set手机(intent.getStringExtra("手机"));
        mUserBean.set生日(intent.getStringExtra("生日"));
        mUserBean.set昵称(intent.getStringExtra("昵称"));
        mUserBean.set类型(intent.getStringExtra("人"));
        findViewById();
        initTitle();
        initView();
    }

    public static void startActivity(Context context, UserBean userBean) {
        Intent intent = new Intent(context,DetailActivity.class);
        intent.putExtra("类型",userBean.get类型());
        intent.putExtra("昵称",userBean.get昵称());
        intent.putExtra("性别",userBean.get性别());
        intent.putExtra("年龄",userBean.get年龄());
        intent.putExtra("生日",userBean.get生日());
        intent.putExtra("手机",userBean.get手机());
        context.startActivity(intent);
    }

    private void findViewById() {
        mTvTitle = (TextView) findViewById(R.id.title_tv_title);
        mIvAvatar = (ImageView) findViewById(R.id.detail_iv_avatar);
        mTvAge = (TextView) findViewById(R.id.detail_tv_age);
        mTvBirthday = (TextView) findViewById(R.id.detail_tv_birthday);
        mTvGender = (TextView) findViewById(R.id.detail_tv_gender);
        mTvPhone = (TextView) findViewById(R.id.detail_tv_phone);
        mTvName = (TextView) findViewById(R.id.detail_tv_name);
    }

    private void initTitle() {
        mTvTitle.setText("详细资料");
    }

    private void initView() {
        mTvName.setText(mUserBean.get昵称());
        mTvPhone.setText("手机：" + mUserBean.get手机());
        mTvGender.setText("性别：" + mUserBean.get性别());
        mTvAge.setText("年龄：" + mUserBean.get年龄());
        mTvBirthday.setText("生日：" + mUserBean.get生日());
    }

    public void onBack(View v) {
        this.finish();
    }
}
