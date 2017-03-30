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
 * Created by jason on 2017/3/31.
 */

public class EquipmentDetailActivity extends FragmentActivity{

    private static final String TAG = EquipmentDetailActivity.class.getSimpleName();
    private TextView mTvTitle;
    private TextView mTvName;
    private TextView mTvNumber;
    private TextView mTvPicture;
    private ImageView mIvAvatar;
    private UserBean mUserBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_datail);
        Intent intent = this.getIntent();
        mUserBean = new UserBean();
        mUserBean.set昵称(intent.getStringExtra("昵称"));
        mUserBean.set类型(intent.getStringExtra("类型"));
        mUserBean.setHMI设备绘图(intent.getStringExtra("HMI设备绘图"));
        mUserBean.set设备序列号(intent.getStringExtra("设备序列号"));
        findViewById();
        initView();
    }

    public static void startActivity(Context context, UserBean userBean) {
        Intent intent = new Intent(context,EquipmentDetailActivity.class);
        intent.putExtra("类型",userBean.get类型());
        intent.putExtra("昵称",userBean.get昵称());
        intent.putExtra("设备序列号",userBean.get设备序列号());
        intent.putExtra("HMI设备绘图",userBean.getHMI设备绘图());
        context.startActivity(intent);
    }

    private void findViewById() {
        mIvAvatar = (ImageView) findViewById(R.id.equipment_detail_iv_avatar);
        mTvTitle = (TextView) findViewById(R.id.title_tv_title);
        mTvName = (TextView) findViewById(R.id.equipment_detail_tv_name);
        mTvNumber = (TextView) findViewById(R.id.equipment_detail_tv_number);
        mTvPicture = (TextView) findViewById(R.id.equipment_detail_tv_picture);
    }

    private void initView() {
        mTvTitle.setText("设备详情");
        mTvName.setText(mUserBean.get昵称());
        mTvNumber.setText("设备序列号：" + mUserBean.get设备序列号());
        mTvPicture.setText("HMI设备绘图：" + mUserBean.getHMI设备绘图());
    }

   public void onBack(View v) {
       this.finish();
   }
}
