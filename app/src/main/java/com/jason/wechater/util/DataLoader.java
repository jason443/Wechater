package com.jason.wechater.util;

import com.jason.wechater.bean.ContactBean;
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
