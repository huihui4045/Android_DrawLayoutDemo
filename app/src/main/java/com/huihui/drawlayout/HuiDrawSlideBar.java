package com.huihui.drawlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.LinearLayout;

/**
 * Created by gavin
 * Time 2017/7/5  15:44
 * Email:molu_clown@163.com
 *
 */

public class HuiDrawSlideBar extends LinearLayout {

    private float maxTranslationX;

    private  boolean  opened=false;

    public HuiDrawSlideBar(Context context) {
        this(context,null);
    }

    public HuiDrawSlideBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context,attrs);
    }



    private void init(Context context, AttributeSet attrs) {
        setOrientation(VERTICAL);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SideBar);
            maxTranslationX = a.getDimension(R.styleable.SideBar_maxTranslationX, 0);
            a.recycle();
        }
    }

    public void setTouchY(float y, float slideOffset) {

        //遍历全部子控件  给每一个子控件进行偏移
        //如果slideOffset =1   侧滑菜单全部出来了
        opened = slideOffset == 1;

        for (int i = 0; i < getChildCount(); i++) {
            View chlid = getChildAt(i);
            chlid.setPressed(false);
            //要判断  y坐落在哪一个子控件    松手的那一刻  进行回调  跳转其他页面
            boolean isHover = opened && y > chlid.getTop() && y < chlid.getBottom();
            if (isHover) {
                chlid.setPressed(true);
                //回调调用层
            }
            //偏移方法
            apply(getParent(), chlid, y, slideOffset);

        }

    }

    private void apply(ViewParent parent, View chlid, float y, float slideOffset) {
        //偏移距离
        float translationX=0;
        //
        int centerY=chlid.getTop()+chlid.getHeight()/2;
        //控件中心点 局手指的距离
        float distance=Math.abs(y-centerY);
        float scale=distance/getHeight()*3;//3   放大系数
        translationX=maxTranslationX-scale*maxTranslationX;
        Log.i("tuch","maxTranslationX  "+maxTranslationX+"   touchY  "+ y+"   slideOffset  "+ slideOffset + "   偏移量  " + translationX * slideOffset);
        chlid.setTranslationX(translationX);
    }


    /**
     * 手指 弹起来
     */
    public void onMotionUp() {
        for (int i=0;opened&&i<getChildCount();i++) {
            View view = getChildAt(i);
            if (view.isPressed()) {
                view.performClick();
                //回调操作


                Log.e("HuiDrawSlideBar","performClick 出发了");

            }

        }
    }

}
