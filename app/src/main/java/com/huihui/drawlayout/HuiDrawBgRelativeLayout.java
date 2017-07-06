package com.huihui.drawlayout;

import android.graphics.Color;
import android.widget.RelativeLayout;

/**
 * Created by gavin
 * Time 2017/7/5  15:47
 * Email:molu_clown@163.com
 */

public class HuiDrawBgRelativeLayout extends RelativeLayout {

    private HuiDrawBgView myDrawBgView;

    private HuiDrawSlideBar mHuiDrawSlideBar;

    public HuiDrawBgRelativeLayout(HuiDrawSlideBar huiDrawSlideBar) {

        super(huiDrawSlideBar.getContext());

        this.mHuiDrawSlideBar = huiDrawSlideBar;


        setLayoutParams(huiDrawSlideBar.getLayoutParams());

        //背景先添加进去
        myDrawBgView = new HuiDrawBgView(getContext());
        addView(myDrawBgView, 0, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        //把slideBar  的背景颜色取出来    设置给 myDrawBgView   slideBar弄成透明
        myDrawBgView.setColor(huiDrawSlideBar.getBackground());
        huiDrawSlideBar.setBackgroundColor(Color.TRANSPARENT);

        addView(huiDrawSlideBar, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

    }

    /**
     * 传递偏移Y
     *
     * @param y
     * @param slideOffset
     */
    public void setTouchY(float y, float slideOffset) {
        mHuiDrawSlideBar.setTouchY(y, slideOffset);
        myDrawBgView.setTouchY(y,slideOffset);
        // myDrawSlideBar.setTouchY(y, slideOffset);


    }

    public void onMotionUp() {
        mHuiDrawSlideBar.onMotionUp();

    }

}
