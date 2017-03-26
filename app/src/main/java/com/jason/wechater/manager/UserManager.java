package com.jason.wechater.manager;

import android.content.Context;

import com.jason.wechater.bean.UserBean;

/**
 * Created by jason on 2017/3/27.
 */

public class UserManager {

    private static final String TAG = UserManager.class.getSimpleName();
    private static UserManager mUserManager;
    private UserBean userBean;

    public static UserManager getInstance() {
        if (mUserManager == null) {
            synchronized (UserManager.class) {
                if (mUserManager == null) {
                    mUserManager = new UserManager();
                }
            }
        }
        return mUserManager;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
