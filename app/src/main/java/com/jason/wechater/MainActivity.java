package com.jason.wechater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jason.wechater.bean.ConverBean;
import com.jason.wechater.common.ActionItem;
import com.jason.wechater.dialog.TitlePopup;
import com.jason.wechater.fragment.ContactFragment;
import com.jason.wechater.fragment.FindFragment;
import com.jason.wechater.fragment.HomeFragment;
import com.jason.wechater.fragment.MeFragment;
import com.jason.wechater.widght.CustomDialog;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";
    private TextView mTvTitle;
    private ImageView mIvRight;
    private ImageView mIvLeft;
    private TitlePopup mTitlePopup;
    private CustomDialog dialog;

    private TextView unreaMsgdLabel;// 未读消息textview
    private TextView unreadAddressLable;// 未读通讯录textview
    private TextView unreadFindLable;// 发现

    private HomeFragment mHomeFragment;
    private FindFragment mFindFragment;
    private MeFragment mMeFragment;
    private ContactFragment mContactFragment;
    private Fragment[] mFragments;

    private ImageView[] imagebuttons;
    private TextView[] textviews;
    private String connectMsg = "";
    private int index;
    private int currentTabIndex;// 当前fragment的index


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        initViews();
        initTitlePopup();
        initTabView();
    }

    private void findViewById() {
        mTvTitle = (TextView) findViewById(R.id.title_tv_title);
        mIvRight = (ImageView) findViewById(R.id.title_iv_right);
        mIvLeft = (ImageView) findViewById(R.id.title_iv_left);
    }

    private void initViews() {
        mTvTitle.setText("微信");
        mIvLeft.setVisibility(View.GONE);
        mIvRight.setImageResource(R.drawable.icon_add);
        mIvRight.setOnClickListener(this);
    }

    private TitlePopup.OnItemOnClickListener onitemClick = new TitlePopup.OnItemOnClickListener() {
        @Override
        public void onItemClick(ActionItem item, int position) {
            switch(position) {
                case 0: //设备通讯
                  //  ChatActivity.startActivity(MainActivity.this,"设备1");
                    showDialog();
                    break;
            }
        }
    };

    private void initTitlePopup() {
        mTitlePopup = new TitlePopup(this, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        mTitlePopup.setItemOnClickListener(onitemClick);
        mTitlePopup.addAction(new ActionItem(this, "设备通讯",
                R.drawable.icon_menu_group));
        mTitlePopup.addAction(new ActionItem(this, "添加朋友",
                R.drawable.icon_menu_addfriend));
        mTitlePopup.addAction(new ActionItem(this, "扫一扫",
                R.drawable.icon_menu_sao));
        mTitlePopup.addAction(new ActionItem(this, "微信支付",
                R.drawable.abv));
    }

    private void initTabView() {
        unreaMsgdLabel = (TextView) findViewById(R.id.unread_msg_number);
        unreadAddressLable = (TextView) findViewById(R.id.unread_address_number);
        unreadFindLable = (TextView) findViewById(R.id.unread_find_number);
        mHomeFragment = new HomeFragment();
        mContactFragment = new ContactFragment();
        mMeFragment = new MeFragment();
        mFindFragment = new FindFragment();
        mFragments = new Fragment[] {
                mHomeFragment,mContactFragment, mFindFragment, mMeFragment
        };

        imagebuttons = new ImageView[4];
        imagebuttons[0] = (ImageView) findViewById(R.id.ib_weixin);
        imagebuttons[1] = (ImageView) findViewById(R.id.ib_contact_list);
        imagebuttons[2] = (ImageView) findViewById(R.id.ib_find);
        imagebuttons[3] = (ImageView) findViewById(R.id.ib_profile);

        imagebuttons[0].setSelected(true);
        textviews = new TextView[4];
        textviews[0] = (TextView) findViewById(R.id.tv_weixin);
        textviews[1] = (TextView) findViewById(R.id.tv_contact_list);
        textviews[2] = (TextView) findViewById(R.id.tv_find);
        textviews[3] = (TextView) findViewById(R.id.tv_profile);
        textviews[0].setTextColor(0xFF45C01A);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mHomeFragment)
                .add(R.id.fragment_container, mContactFragment)
                .add(R.id.fragment_container, mFindFragment)
                .add(R.id.fragment_container, mMeFragment)
                .hide(mContactFragment).hide(mFindFragment)
                .hide(mMeFragment).show(mHomeFragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_iv_right:
                Log.d(TAG, "onClick: ");
                mTitlePopup.show(findViewById(R.id.layout_bar));
                break;
            default:
        }
    }

    public void onTabClicked(View view) {
        mIvRight.setVisibility(View.GONE);
        switch (view.getId()) {
            case R.id.re_weixin:
                mIvRight.setVisibility(View.VISIBLE);
                index = 0;
                if (mHomeFragment != null) {
                    mHomeFragment.refresh();
                }
                mTvTitle.setText(R.string.app_name);
                mIvRight.setImageResource(R.drawable.icon_add);
                break;
            case R.id.re_contact_list:
                index = 1;
                mTvTitle.setText(R.string.contacts);
                mIvRight.setVisibility(View.VISIBLE);
                mIvRight.setImageResource(R.drawable.icon_titleaddfriend);
                break;
            case R.id.re_find:
                index = 2;
                mTvTitle.setText(R.string.discover);
                break;
            case R.id.re_profile:
                index = 3;
                mTvTitle.setText(R.string.me);
                break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager()
                    .beginTransaction();
            trx.hide(mFragments[currentTabIndex]);
            if (!mFragments[index].isAdded()) {
                trx.add(R.id.fragment_container, mFragments[index]);
            }
            trx.show(mFragments[index]).commit();
        }
        imagebuttons[currentTabIndex].setSelected(false);
        // 把当前tab设为选中状态
        imagebuttons[index].setSelected(true);
        textviews[currentTabIndex].setTextColor(0xFF999999);
        textviews[index].setTextColor(0xFF45C01A);
        currentTabIndex = index;
    }

    private void showDialog() {
        dialog = new CustomDialog(this);
        final EditText editText = (EditText) dialog.getEditText();//方法在CustomDialog中实现
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "设备" + editText.getText().toString();
                mHomeFragment.addConver(new ConverBean(msg));
                ChatActivity.startActivity(MainActivity.this,msg);
                dialog.dismiss();
            }
        });
        dialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
