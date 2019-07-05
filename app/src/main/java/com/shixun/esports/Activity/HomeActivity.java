package com.shixun.esports.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;

import com.shixun.esports.Fragment.HomepageFragment;
import com.shixun.esports.Fragment.MessageFragment;
import com.shixun.esports.Fragment.MineFragment;
import com.shixun.esports.R;

public class HomeActivity extends FragmentActivity implements View.OnClickListener {
    private LinearLayout mTabWeixin;
    private LinearLayout mTabFri;
    private LinearLayout mTabMe;

    private ImageButton mImgWeixin;
    private ImageButton mImgFri;
    private ImageButton mImgMe;

    private Fragment mtab1;
    private Fragment mtab2;
    private Fragment mtab3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initView();
        initEvent();

        setSelect(0);
    }

    private void initEvent() {
        mTabWeixin.setOnClickListener(this);
        mTabFri.setOnClickListener(this);;
        mTabMe.setOnClickListener(this);
    }

    private void initView() {
        mTabWeixin = (LinearLayout)findViewById(R.id.id_tab_ui);
        mTabFri = (LinearLayout)findViewById(R.id.id_tab_fri);
        mTabMe = (LinearLayout)findViewById(R.id.id_tab_me);

        mImgWeixin = (ImageButton) findViewById(R.id.id_tab_ui_img);
        mImgFri = (ImageButton) findViewById(R.id.id_tab_fri_img);
        mImgMe = (ImageButton) findViewById(R.id.id_tab_me_img);


    }

    private void setSelect(int i)
    {

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        hideFragment(transaction);
        //设置内容区域
        switch (i)
        {
            case 0:
                if(mtab1==null){
                    mtab1 = new HomepageFragment();
                    transaction.add(R.id.id_content,mtab1);
                }else
                {
                    transaction.show(mtab1);
                }
                mImgWeixin.setImageResource(R.mipmap.ic_launcher_round);
                break;
            case 1:
                if(mtab2==null){
                    mtab2 = new MessageFragment();
                    transaction.add(R.id.id_content,mtab2);
                }else
                {
                    transaction.show(mtab2);
                }
                mImgFri.setImageResource(R.mipmap.ic_launcher_round);
                break;
            case 2:
                if(mtab3==null){
                    mtab3 = new MineFragment();
                    transaction.add(R.id.id_content,mtab3);
                }else
                {
                    transaction.show(mtab3);

                }
                mImgMe.setImageResource(R.mipmap.ic_launcher_round);
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if(mtab1 !=null)
        {
            transaction.hide(mtab1);
        }
        if(mtab2 !=null)
        {
            transaction.hide(mtab2);
        }
        if(mtab3 !=null)
        {
            transaction.hide(mtab3);
        }

    }

    @Override
    public void onClick(View view) {

        resetImg();
        switch (view.getId())
        {
            case R.id.id_tab_ui:
                setSelect(0);
                break;
            case R.id.id_tab_fri:
                setSelect(1);
                break;
            case R.id.id_tab_me:
                setSelect(2);
                break;
        }
    }

    private void resetImg() {
        mImgWeixin.setImageResource(R.mipmap.ic_launcher);
        mImgFri.setImageResource(R.mipmap.ic_launcher);
        mImgMe.setImageResource(R.mipmap.ic_launcher);
    }
}
