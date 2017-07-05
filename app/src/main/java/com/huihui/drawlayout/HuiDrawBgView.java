package com.huihui.drawlayout;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by gavin
 * Time 2017/7/5  15:45
 * Email:molu_clown@163.com
 * 做背景的蓝色动态效果
 */

public class HuiDrawBgView extends View {
    private Paint paint;
    private Path path;

    public HuiDrawBgView(Context context) {
        this(context,null);
    }

    public HuiDrawBgView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }



    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        path = new Path();
    }
    //percent 侧滑菜单   出来的百分比
    public void setTouchY(float y, float percent) {

        path.reset();

        float width=getWidth()*percent;
        float height=getHeight();
        float offsetY=height/8;
        float x=width/2;
        path.lineTo(x,-offsetY);
        path.quadTo(width*3/2,y,x,height+offsetY);
        path.lineTo(0,height);
        path.close();
        invalidate();

    }


    /**
     * 传递颜色
     * @param color
     */
    public void setColor(int color) {
        paint.setColor(color);
    }

    public void setColor(Drawable color) {
        if (color instanceof ColorDrawable) {
            ColorDrawable colorDrawable= (ColorDrawable) color;
            paint.setColor(colorDrawable.getColor());
        }else {
            //作业    实现背景图片 的  变换
        }
    }
}
