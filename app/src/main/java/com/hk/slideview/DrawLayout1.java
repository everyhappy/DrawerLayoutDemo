package com.hk.slideview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;
import java.util.List;

import adapter.ContentAdapter;
import model.ContentModel;

public class DrawLayout1 extends Activity {

    private DrawerLayout drawerLayout;
    private RelativeLayout leftLayout;
    private RelativeLayout rightLayout;
    private List<ContentModel> list;
    private ContentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_draw_layout1);
        drawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
//        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
//                Gravity.RIGHT);
        leftLayout=(RelativeLayout) findViewById(R.id.left);
        rightLayout=(RelativeLayout) findViewById(R.id.right);
        ListView listView=(ListView) leftLayout.findViewById(R.id.left_listview);
        initData();
        adapter=new ContentAdapter(this, list);
        listView.setAdapter(adapter);
        initEvents();
    }
    private void initData() {
        list=new ArrayList<ContentModel>();
        list.add(new ContentModel(R.drawable.doctoradvice2, "新闻"));
        list.add(new ContentModel(R.drawable.infusion_selected, "订阅"));
        list.add(new ContentModel(R.drawable.mypatient_selected, "图片"));
        list.add(new ContentModel(R.drawable.mywork_selected, "视频"));
        list.add(new ContentModel(R.drawable.nursingcareplan2, "跟帖"));
        list.add(new ContentModel(R.drawable.personal_selected, "投票"));
    }

    private void initEvents() {
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                Log.d("HK","onDrawerSlide slideOffset="+slideOffset);
                View mContent = drawerLayout.getChildAt(0);
                View mRightContent = drawerLayout.getChildAt(2);
                View mMenu = drawerView;
                float scale = 1 - slideOffset;
                float rightScale = 0.8f + scale * 0.2f;
                if (drawerView.getTag().equals("LEFT")){
                    float leftScale = 1 - 0.3f * scale;

                    ViewHelper.setScaleX(mMenu, leftScale);
                    ViewHelper.setScaleY(mMenu, leftScale);
                    ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));

                    ViewHelper.setTranslationX(mContent,
                            mMenu.getMeasuredWidth() * (1 - scale));
                    ViewHelper.setPivotX(mContent, 0);
                    ViewHelper.setPivotY(mContent,
                            mContent.getMeasuredHeight() / 2);
                    mContent.invalidate();
                    ViewHelper.setScaleX(mContent, rightScale);
                    ViewHelper.setScaleY(mContent, rightScale);
                } else if(drawerView.getTag().equals("RIGHT")){
                    float rScale = 1 - 0.3f * scale;
                    ViewHelper.setScaleX(mRightContent,rScale);
                    ViewHelper.setScaleY(mRightContent,rScale);
                    ViewHelper.setAlpha(mRightContent,0.6f + 0.4f * (1 - scale));

                    ViewHelper.setTranslationX(mContent,
                            -mMenu.getMeasuredWidth() * slideOffset);
                    ViewHelper.setPivotX(mContent, mRightContent.getMeasuredWidth());
                    ViewHelper.setPivotY(mContent,
                            mContent.getMeasuredHeight() / 2);
                    mContent.invalidate();
                    ViewHelper.setScaleX(mContent, rightScale);
                    ViewHelper.setScaleY(mContent, rightScale);
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                Log.d("HK","onDrawerOpened");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Log.d("HK","onDrawerClosed");
//                drawerLayout.setDrawerLockMode(
//                        DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                Log.d("HK","onDrawerStateChanged");
            }
        });
    }
}
