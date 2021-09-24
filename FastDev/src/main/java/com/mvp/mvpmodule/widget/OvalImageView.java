package com.mvp.mvpmodule.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.mvp.mvpmodule.R;


public class OvalImageView extends androidx.appcompat.widget.AppCompatImageView {

    /*圆角的半径，依次为左上角xy半径，右上角，右下角，左下角*/
    //此处可根据自己需要修改大小
    private float ul_radius;
    private float ur_radius;
    private float ll_radius;
    private float lr_radius;
    private float radius;
    private float strokeSize;
    private int strokeColor;
    private boolean isAllRound = false;
    private boolean isOval;
    private boolean isRoundedRectangle = false;


    public OvalImageView(Context context) {
        this(context, null);
    }

    public OvalImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OvalImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setScaleType(ScaleType.CENTER_CROP);

        // 获取属性集合 TypedArray
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.OvalView);

        // 获取属性

        strokeColor = typedArray.getColor(R.styleable.OvalView_stroke_color, Color.TRANSPARENT);
        strokeSize = typedArray.getDimension(R.styleable.OvalView_stroke_size, 0);

        isOval = typedArray.getBoolean(R.styleable.OvalView_is_oval, false);
        if (isOval)
            return;

        isRoundedRectangle = typedArray.getBoolean(R.styleable.OvalView_is_round_rectangle, false);
        if (isRoundedRectangle)
            return;

        radius = typedArray.getDimension(R.styleable.OvalView_all_radius, 0);
        if (radius != 0) {
            isAllRound = true;
        } else {
            ul_radius = typedArray.getDimension(R.styleable.OvalView_ul_radius, 0);
            ur_radius = typedArray.getDimension(R.styleable.OvalView_ur_radius, 0);
            ll_radius = typedArray.getDimension(R.styleable.OvalView_ll_radius, 0);
            lr_radius = typedArray.getDimension(R.styleable.OvalView_lr_radius, 0);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        int w = this.getWidth();
        int h = this.getHeight();
        /*向路径中添加圆角矩形。radii数组定义圆角矩形的四个圆角的x,y半径。radii长度必须为8*/
        if (isOval)
            path.addRoundRect(new RectF(0, 0, w, h), w / 2, h / 2, Path.Direction.CW);
        else if (isRoundedRectangle) {
            int s = Math.min(w, h);
            path.addRoundRect(new RectF(0, 0, w, h), s / 2, s / 2, Path.Direction.CW);
        } else if (isAllRound)
            path.addRoundRect(new RectF(0, 0, w, h), radius, radius, Path.Direction.CW);
        else
            path.addRoundRect(new RectF(0, 0, w, h),
                    new float[]{ul_radius, ul_radius,
                            ur_radius, ur_radius,
                            lr_radius, lr_radius,
                            ll_radius, ll_radius,}, Path.Direction.CW);

        canvas.clipPath(path);
        super.onDraw(canvas);

        if (strokeSize > 0) {
            Paint paint = new Paint();
            paint.setStrokeWidth(strokeSize);
            paint.setColor(strokeColor);
            paint.setStyle(Paint.Style.STROKE);
            paint.setAntiAlias(true);
            canvas.drawPath(path, paint);
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Path path = new Path();
        int w = this.getWidth();
        int h = this.getHeight();
        /*向路径中添加圆角矩形。radii数组定义圆角矩形的四个圆角的x,y半径。radii长度必须为8*/
        if (isOval)
            path.addRoundRect(new RectF(0, 0, w, h), w / 2, h / 2, Path.Direction.CW);
        else if (isRoundedRectangle) {
            int s = Math.min(w, h);
            path.addRoundRect(new RectF(0, 0, w, h), s / 2, s / 2, Path.Direction.CW);
        } else if (isAllRound)
            path.addRoundRect(new RectF(0, 0, w, h), radius, radius, Path.Direction.CW);
        else
            path.addRoundRect(new RectF(0, 0, w, h),
                    new float[]{ul_radius, ul_radius,
                            ur_radius, ur_radius,
                            lr_radius, lr_radius,
                            ll_radius, ll_radius,}, Path.Direction.CW);

        if (getBackground() != null && w > 0 && h > 0) {
            if (getBackground() instanceof ColorDrawable) {
                Paint paint = new Paint();
                ColorDrawable colorDrawable = (ColorDrawable) getBackground();
                paint.setColor(colorDrawable.getColor());

                paint.setAntiAlias(true);

                canvas.drawPath(path, paint);

                Bitmap output = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                Canvas canvas2 = new Canvas(output);
                canvas2.drawPath(path, paint);
                setBackground(new BitmapDrawable(output));
            }
        }

        canvas.clipPath(path);
        super.dispatchDraw(canvas);
        if (isShowStroke && strokeSize > 0) {
            Paint paint = new Paint();
            paint.setStrokeWidth(strokeSize);
            paint.setColor(strokeColor);
            paint.setStyle(Paint.Style.STROKE);
            paint.setAntiAlias(true);
            canvas.drawPath(path, paint);
        }
    }

    public void setAllRadius(float radius) {
        isOval = false;
        isAllRound = true;
        this.radius = radius;
        invalidate();
    }

    boolean isShowStroke = true;

    public void showStroke(boolean showStroke) {
        this.isShowStroke = showStroke;
        invalidate();
    }

    public void setRadius(float ul_radius,float ur_radius,float ll_radius,float lr_radius){
        this.ul_radius=ul_radius;
        this.ur_radius=ur_radius;
        this.ll_radius=ll_radius;
        this.lr_radius=lr_radius;
        invalidate();
    }
    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        invalidate();
    }
}