package com.jason.wechater.bean;

/**
 * Created by jason on 2017/3/26.
 */

public class UserBean {


    /**
     * 类型 : 人
     * 昵称 : huangyishengC
     * 性别 : 女
     * 年龄 : 12
     * 生日 : 7月12日
     * 手机 : 13533241542
     */



    private String 类型;
    private String 昵称;
    private String 性别;
    private String 年龄;
    private String 生日;
    private String 手机;
    /**
     * 设备序列号 : abcdefghijklmnopqrstuvwxyz
     * HMI设备绘图 : 12789.txt
     */

    private String 设备序列号;
    private String HMI设备绘图;

    public String get类型() {
        return 类型;
    }

    public void set类型(String 类型) {
        this.类型 = 类型;
    }

    public String get昵称() {
        return 昵称;
    }

    public void set昵称(String 昵称) {
        this.昵称 = 昵称;
    }

    public String get性别() {
        return 性别;
    }

    public void set性别(String 性别) {
        this.性别 = 性别;
    }

    public String get年龄() {
        return 年龄;
    }

    public void set年龄(String 年龄) {
        this.年龄 = 年龄;
    }

    public String get手机() {
        return 手机;
    }

    public void set手机(String 手机) {
        this.手机 = 手机;
    }

    public String get生日() {
        return 生日;
    }

    public void set生日(String 生日) {
        this.生日 = 生日;
    }


    public String get设备序列号() {
        return 设备序列号;
    }

    public void set设备序列号(String 设备序列号) {
        this.设备序列号 = 设备序列号;
    }

    public String getHMI设备绘图() {
        return HMI设备绘图;
    }

    public void setHMI设备绘图(String HMI设备绘图) {
        this.HMI设备绘图 = HMI设备绘图;
    }
}
