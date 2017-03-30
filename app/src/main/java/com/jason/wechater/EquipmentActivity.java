package com.jason.wechater;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jason.wechater.bean.DrawBean;
import com.jason.wechater.bean.UserBean;
import com.jason.wechater.manager.UserManager;
import com.jason.wechater.util.DataLoader;
import com.jason.wechater.widght.MyDrawView;

/**
 * Created by jason on 2017/3/27.
 */

public class EquipmentActivity extends FragmentActivity{

    private static final String TAG = EquipmentActivity.class.getSimpleName();
    private UserBean mUserBean;
    private DrawBean mDrawBean;
    private MyDrawView mDrawView;
    private TextView mTvTitle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);
        Intent intent = this.getIntent();
        mUserBean = new UserBean();
        mUserBean.set类型(intent.getStringExtra("类型"));
        mUserBean.set昵称(intent.getStringExtra("昵称"));
        mUserBean.set设备序列号(intent.getStringExtra("设备序列号"));
        mUserBean.setHMI设备绘图(intent.getStringExtra("HMI设备绘图"));
        findViewById();
        initData();
        initView();
    }

    public static void startActvity(Context context, UserBean userBean) {
        Intent intent = new Intent(context,EquipmentActivity.class);
        intent.putExtra("类型",userBean.get类型());
        intent.putExtra("昵称",userBean.get昵称());
        intent.putExtra("设备序列号",userBean.get设备序列号());
        intent.putExtra("HMI设备绘图",userBean.getHMI设备绘图());
        context.startActivity(intent);
    }

    private void findViewById() {
        mDrawView = (MyDrawView) findViewById(R.id.equipment_dv_body);
        mTvTitle = (TextView) findViewById(R.id.title_tv_title);
    }

    private void initData() {
        mDrawBean = DataLoader.getDrawData(mUserBean.getHMI设备绘图());
      //  mDrawBean = DataLoader.getDrawData("12789.txt");
    }

    private void initView() {
        mTvTitle.setText(mUserBean.get昵称());
        if (mDrawBean.get绘制图片() != null) {
            mDrawView.drawPicture(mDrawBean.get绘制图片().getX(),mDrawBean.get绘制图片().getY(),
                    mDrawBean.get绘制图片().getName());
        }
        if (mDrawBean.get绘制圆形() != null) {
            mDrawView.drawCircular(mDrawBean.get绘制圆形().getX(),mDrawBean.get绘制圆形().getY(),
                    mDrawBean.get绘制圆形().getR(),mDrawBean.get绘制圆形().getW(),Color.RED);
        }
        if (mDrawBean.get绘制直线() != null) {
            DrawBean.绘制直线Bean bean = mDrawBean.get绘制直线();
            mDrawView.drawLine(bean.getX1(),bean.getY1(),bean.getX2(),bean.getY2(),bean.getW(),Color.RED);
        }
        if (mDrawBean.get设置背景颜色() != null) {
            DrawBean.设置背景颜色Bean bean = mDrawBean.get设置背景颜色();
            mDrawView.setBBackground(Color.YELLOW);
        }
    }


    public void onBack(View v) {
        this.finish();
    }
}
