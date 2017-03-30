package com.jason.wechater;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jason.wechater.bean.LoginBean;
import com.jason.wechater.manager.UserManager;
import com.jason.wechater.util.CommonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
 * Created by jason on 2017/3/25.
 */

public class LoginActivity extends FragmentActivity{

    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText mEtaccount;
    private EditText mEtPassword;
    private List<LoginBean> mLoginBeans;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEtaccount = (EditText) findViewById(R.id.login_et_account);
        mEtPassword = (EditText) findViewById(R.id.login_et_password);
        initData();
    }

    private void initData() {
        mLoginBeans = new ArrayList<>();
        File urlFile = new File("/sdcard/wechaterData/用户列表.json");
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(urlFile),"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            StringBuffer sb = new StringBuffer();
            while((line=br.readLine()) != null) {
                sb.append(line);
            }
            Log.d(TAG, "initData: " + sb.toString());
            mLoginBeans = CommonParser.parseForList(sb.toString(),"list",LoginBean.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onLogin(View v) {
        String account = mEtaccount.getText().toString();
        String password = mEtPassword.getText().toString();
        Log.d(TAG, "onLogin: " + account + "/" + password);
        if (checkLogin(account,password)) {
//            Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
            LoginBean loginBean = new LoginBean(account,password);
            UserManager.getInstance().setLoginBean(loginBean);
            MainActivity.startActivity(this);
        } else {
            Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkLogin(String account,String password) {
       for (LoginBean lb : mLoginBeans) {
           if (lb.getAccount().equals(account) && lb.getPassword().equals(password)) {
               return true;
           }
       }
        return false;
    }
}
