package com.jason.wechater.db;

import com.jason.wechater.bean.ConverBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2017/3/16.
 */

public class ConverManager {

    private static ConverManager mManager;
    private List<ConverBean> converBeen = new ArrayList<>();

    public static ConverManager getInstance() {
        if (mManager == null) {
            synchronized (ConverManager.class) {
                if (mManager == null) {
                    mManager = new ConverManager();
                }
            }
        }
        return mManager;
    }

    public List<ConverBean> getConverBean() {
        return converBeen;
    }

}
