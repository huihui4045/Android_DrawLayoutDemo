package com.huihui.drawlayout;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by gavin
 * Time 2017/7/5  15:39
 * Email:molu_clown@163.com
 */

public class HuiDrawLayout extends DrawerLayout implements DrawerLayout.DrawerListener {


    /**
     * 侧滑控件
     */
    private HuiDrawSlideBar myDrawSlideBar;
    /****
     * 内容控件
     */
    private View contenView;

    private HuiDrawBgRelativeLayout mHuiDrawBgRelativeLayout;

    public HuiDrawLayout(Context context) {
        super(context);
    }

    public HuiDrawLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HuiDrawLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        initView();

    }

    private void initView() {

        for (int i = 0; i < getChildCount(); i++) {

            View child = getChildAt(i);

            if (child instanceof HuiDrawSlideBar) {

                myDrawSlideBar = (HuiDrawSlideBar) child;
            } else {

                contenView = child;
            }
        }

        removeView(myDrawSlideBar);

        mHuiDrawBgRelativeLayout = new HuiDrawBgRelativeLayout(myDrawSlideBar);


        addView(mHuiDrawBgRelativeLayout);
        addDrawerListener(this);

    }

    private float y;

    private float slideOffset;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        y = ev.getY();

        if (ev.getAction() == MotionEvent.ACTION_UP) {

            closeDrawers();
            mHuiDrawBgRelativeLayout.onMotionUp();

            return super.dispatchTouchEvent(ev);

        }

        if (slideOffset<1){

            return super.dispatchTouchEvent(ev);
        }else {

            //等于  1
            mHuiDrawBgRelativeLayout.setTouchY(y,slideOffset);
        }


        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

        this.slideOffset = slideOffset;

        mHuiDrawBgRelativeLayout.setTouchY(y,slideOffset);
        //针对内容区域进行破偏移
        float contentViewoffset=drawerView.getWidth()*slideOffset/2;
        contenView.setTranslationX(contentViewoffset);
    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
