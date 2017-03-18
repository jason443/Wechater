package com.jason.wechater.bean;

import android.graphics.Bitmap;

/**
 * Created by jason on 2017/3/16.
 */

public class MsgBean {

    public static final int TEXT = 0;
    public static final int PHOTO = 1;
    public static final int SEND = 2;
    public static final int RECEIVED = 3;

    private String msg;
    private Bitmap photo;
    private int type1;
    private int type2;

    public int getType1() {
        return type1;
    }

    public void setType1(int type1) {
        this.type1 = type1;
    }

    public int getType2() {
        return type2;
    }

    public void setType2(int type2) {
        this.type2 = type2;
    }

    public MsgBean(String msg, Bitmap photo, int type1, int type2) {
        this.msg = msg;
        this.photo = photo;
        this.type1 = type1;
        this.type2 = type2;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }


}
