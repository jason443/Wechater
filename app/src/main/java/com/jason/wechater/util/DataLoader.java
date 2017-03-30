package com.jason.wechater.util;

import android.util.Log;

import com.jason.wechater.bean.ContactBean;
import com.jason.wechater.bean.DrawBean;
import com.jason.wechater.bean.Msg1Bean;
import com.jason.wechater.bean.UserBean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2017/3/26.
 */

public class DataLoader {

    private static final String TAG = DataLoader.class.getSimpleName();

    public static DrawBean getDrawData(String filePath) {
        String url = "/sdcard/wechaterData/HMI绘图/" + filePath;
        File urlFile = new File(url);
        DrawBean drawBean = null;
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(urlFile),"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            StringBuffer sb = new StringBuffer();
            while((line=br.readLine()) != null) {
                sb.append(line);
            }
            return CommonParser.parseForSingle(sb.toString(),DrawBean.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return drawBean;
    }

    public static List<Msg1Bean> getMsgs(String sender,String receiver) {
        List<Msg1Bean> returnData = null;
        String url = "/sdcard/wechaterData/聊天记录/" + sender + "_" + receiver + ".txt";
        File urlFile = new File(url);
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(urlFile),"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            StringBuffer sb = new StringBuffer();
            while((line=br.readLine()) != null) {
                sb.append(line);
            }
            returnData = CommonParser.parseForList(sb.toString(),"list",Msg1Bean.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnData;
    }

    /*
    * 获取好友列表（仅列表）
    * */
    public static List<ContactBean> getContactsList(String userAccount) {
        String listUrl = "/sdcard/wechaterData/" + userAccount + "/好友列表.txt";
        File urlFile = new File(listUrl);
        List<ContactBean> contacts = null;
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(urlFile),"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            StringBuffer sb = new StringBuffer();
            while((line=br.readLine()) != null) {
                sb.append(line);
            }
            contacts = CommonParser.parseForList(sb.toString(),"list",ContactBean.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    /*
    *
    * 获取好友列表（包含详细信息）*/
    public static List<UserBean> getContacts(String userAccount) {
        String listUrl = "/sdcard/wechaterData/" + userAccount + "/好友列表.txt";
        File urlFile = new File(listUrl);
        List<ContactBean> contacts = null;
        List<UserBean> returnData = new ArrayList<>();
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(urlFile),"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            StringBuffer sb = new StringBuffer();
            while((line=br.readLine()) != null) {
                sb.append(line);
            }
            contacts = CommonParser.parseForList(sb.toString(),"list",ContactBean.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (contacts != null) {
            for (ContactBean contactBean : contacts) {
                returnData.add(getUser(contactBean.getAccount()));
            }
        }
        return returnData;
    }

    public static UserBean getUser(String userAccount) {
        String url = "/sdcard/wechaterData/" + userAccount + "/我.txt";
        File urlFile = new File(url);
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(urlFile),"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            StringBuffer sb = new StringBuffer();
            while((line=br.readLine()) != null) {
                sb.append(line);
            }
            Log.d(TAG, "getUser: " + sb.toString());
            return CommonParser.parseForSingle(sb.toString(),UserBean.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
