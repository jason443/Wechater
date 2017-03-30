package com.jason.wechater.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jason.wechater.R;
import com.jason.wechater.bean.UserBean;
import com.jason.wechater.manager.UserManager;
import com.jason.wechater.util.DataLoader;

/**
 * Created by jason on 2017/3/14.
 */

public class MeFragment extends Fragment {

    public static final String TAG = MeFragment.class.getSimpleName();
    private UserBean mUserBean;
    private TextView mTvName;
    private TextView mTvGender;
    private TextView mTvAge;
    private TextView mTvPhone;
    private TextView mTvBirthday;
    private ImageView mIvAvatar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me,container,false);
        mTvAge = (TextView) view.findViewById(R.id.me_tv_age);
        mTvBirthday = (TextView) view.findViewById(R.id.me_tv_birthday);
        mTvGender = (TextView) view.findViewById(R.id.me_tv_gender);
        mIvAvatar = (ImageView) view.findViewById(R.id.me_iv_avatar);
        mTvName = (TextView) view.findViewById(R.id.me_tv_name);
        mTvPhone = (TextView) view.findViewById(R.id.me_tv_phone);
        initView();
        return view;
    }

    private void initView() {
        mUserBean = DataLoader.getUser(UserManager.getInstance().getLoginBean().getAccount());
        if (mUserBean != null) {
            mTvName.setText(mUserBean.get昵称());
            mTvPhone.setText("手机：" + mUserBean.get手机());
            mTvGender.setText("性别：" + mUserBean.get性别());
            mTvAge.setText("年龄：" + mUserBean.get年龄());
            mTvBirthday.setText("生日：" + mUserBean.get生日());
        }
    }
}
