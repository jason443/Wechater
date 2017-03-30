package com.jason.wechater.bean;

/**
 * Created by jason on 2017/3/27.
 */

public class Msg1Bean {


    /**
     * time : 2016/8/20 23:33
     * content : 你好
     * master : 23549370
     */

    private String time;
    private String content;
    private String master;

    public Msg1Bean(String content, String master) {
        this.content = content;
        this.master = master;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }
}
