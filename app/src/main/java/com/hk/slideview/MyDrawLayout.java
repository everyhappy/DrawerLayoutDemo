package com.hk.slideview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by huangkun on 2016/11/22.
 */
public class MyDrawLayout extends HorizontalScrollView {


    private int mLeftMenuWidth;
    private int mRightMenuWidth;
    private int mContentWidth;
    private ViewGroup mLeftMenu;
    private ViewGroup mContent;
    private ViewGroup mRightMenu;
    private LinearLayout mLiearLayout;
    private boolean once;


    public MyDrawLayout(Context mContext) {

        super(mContext);
    }

    public MyDrawLayout(Context mContext, AttributeSet att) {
        super(mContext,att);
    }

    public MyDrawLayout(Context mContext,AttributeSet att,int defStyleAttr) {
        super(mContext,att,defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("HK","onInterceptTouchEvent called");

        return super.onInterceptTouchEvent(ev);
    }

    @Override
   public boolean onTouchEvent(MotionEvent event) {
       Log.d("HK","onInterceptTouchEvent called");
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                //相对于当前View滑动的距离,正为向左,负为向右
                int distance = getScrollX();
                Log.d("HK","onTouchEvent called distance "+distance);
                if(distance>=0&&distance<mLeftMenuWidth/2) {
                    this.smoothScrollTo(0,0);
                    Log.d("HK","onTouchEvent called smoothScrollTo 00 mLeftMenuWidth"+mLeftMenuWidth);
                } else if(distance>(mLeftMenuWidth+mRightMenuWidth/2)) {
                    this.smoothScrollTo(mLeftMenuWidth+mRightMenuWidth,0);
                    Log.d("HK","onTouchEvent called smoothScrollTo mLeftMenuWidth+mRightMenuWidth 0");
                } else {
                    this.smoothScrollTo(mLeftMenuWidth,0);
                }

                return true;


        }
          return super.onTouchEvent(event);
   }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if(!once) {
            mLiearLayout = (LinearLayout) getChildAt(0);
            mLeftMenu = (ViewGroup) mLiearLayout.getChildAt(0);
            mContent = (ViewGroup) mLiearLayout.getChildAt(1);
            mRightMenu = (ViewGroup) mLiearLayout.getChildAt(2);
            mLeftMenuWidth = mLeftMenu.getMeasuredWidth();
            mRightMenuWidth = mRightMenu.getMeasuredWidth();
            mContentWidth = mContent.getMeasuredWidth();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float scale = l*1.0f/mLeftMenuWidth;
        float scaleLeft =1.0f-0.4f*scale;
        float scaleRight =0.6f+ 0.4f*((l*1.0f-mLeftMenuWidth)/mRightMenuWidth);
        if(l<mLeftMenuWidth) {
            //向右划
            Log.d("HK","onScrollChanged  < l "+l);
            float scaleContent =0.8f+0.2f*scale;
            ViewHelper.setScaleX(mLeftMenu, scaleLeft);
            ViewHelper.setScaleY(mLeftMenu, scaleLeft);
            ViewHelper.setAlpha(mLeftMenu, 0.6f + 0.4f * scaleLeft);
            //ViewHelper.setTranslationX(mLeftMenu, mLeftMenuWidth * scaleLeft * 0.7f);

            ViewHelper.setPivotX(mContent, 0);
            ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
            ViewHelper.setScaleX(mContent, scaleContent);
            ViewHelper.setScaleY(mContent, scaleContent);
        } else if(l>mLeftMenuWidth) {
            //向左划
            Log.d("HK","onScrollChanged  scaleRight< l "+scaleRight);
            ViewHelper.setScaleX(mRightMenu, scaleRight);
            ViewHelper.setScaleY(mRightMenu, scaleRight);
            ViewHelper.setAlpha(mRightMenu, 0.6f + 0.4f * scaleRight);
            float scaleContent =1.0f-0.2f*(scaleRight-0.6f);
            ViewHelper.setPivotX(mContent, mContent.getWidth());
            ViewHelper.setPivotY(mContent, 0);
            ViewHelper.setScaleX(mContent,scaleContent);
            ViewHelper.setScaleY(mContent, scaleContent);
        }
        Log.d("HK","onScrollChanged called  l t oldl oldt"+"_"+l+"_"+t+"_"+oldl+"_"+oldt);
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed)
        {
            this.scrollTo(mLeftMenuWidth, 0);
            Log.d("HK","onLayout called scrollTo mLeftMenuWidth"+ mLeftMenuWidth);
            once = true;
        }
    }
}
