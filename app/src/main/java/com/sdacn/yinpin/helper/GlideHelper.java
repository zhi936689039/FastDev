package com.sdacn.yinpin.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.mvp.mvpmodule.BuildConfig;
import com.mvp.mvpmodule.util.LogUtil;
import com.sdacn.yinpin.R;
import com.sdacn.yinpin.common.constans.Constants;
/**
 * Created by Frank on 2017/3/31.
 * Glide 4.0 正式版
 */

public enum GlideHelper {
    INSTANCE;
    private int defaultImg= R.mipmap.ic_launcher;
    private int defaultAvaterImg= R.mipmap.ic_launcher_round;


    /**
     * 继续加载图片
     */
    public void resumRequestImg(Context mContext){
        Glide.with(mContext).resumeRequests();
    }
    public void pauseRequestImg(Context mContext){
        Glide.with(mContext).pauseRequests();
    }
    //备用 后台头像地址不改变（只做本人头像显示）
    public void loadAvatar(Activity context, ImageView imageView, String url) {
        if(context!=null&&!context.isFinishing()){
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .circleCrop();   //4.0版本圆形
            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .into(imageView);
        }

    }
    public void loadAvatar2(Activity context, ImageView imageView, String url) {
        if(context!=null&&!context.isFinishing()){
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy( DiskCacheStrategy.AUTOMATIC)
                    .circleCrop();   //4.0版本圆形
            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .into(imageView);
        }

    }

    /*-------------------------- url地址图片 ---------------------------------*/
    /**
     * 加载url图片
     *
     * @param imageView
     * @param url
     */
    public void loadImage(Activity context,ImageView imageView, String url) {
        if(context!=null&&!context.isFinishing()){
            RequestOptions options = new RequestOptions()
                    .placeholder(defaultImg)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            LogUtil.e(Constants.logTag,"图片加载失败:"+e.toString(), BuildConfig.DEBUG);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(imageView);
        }
    }
    public void loadImage2(Context context,ImageView imageView, String url) {
        if(context!=null){
            RequestOptions options = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .into(imageView);
        }
    }

    /**
     * 根据设置尺寸加载url
     * @param imageView
     * @param url
     * @param width
     * @param height
     */
    public void loadImage(Activity context,ImageView imageView, String url, int width, int height) {
        if(context!=null&&!context.isFinishing()){
            RequestOptions options = new RequestOptions()
                    .placeholder(defaultImg)
                    .centerCrop()
                    .override(width,height);
            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .into(imageView);
        }

    }

    /**
     * 加载圆形图片
     *
     * @param imageView
     * @param url
     */
    public void loadCircleImage(Context context,ImageView imageView, String url) {
        RequestOptions options = new RequestOptions()
                .placeholder(defaultAvaterImg)
                .centerCrop()
                .circleCrop();   //4.0版本圆形
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }
    /**
     * 带尺寸加载圆形图片
     *
     * @param imageView
     * @param url
     */
    public void loadCircleImage(Context context,ImageView imageView, String url, int width, int height) {
        RequestOptions options= new RequestOptions()
                .placeholder(defaultAvaterImg)
                .centerCrop()
                .circleCrop()
                .override(width,height);
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }


    /*-------------------------- 资源图片 ---------------------------------*/
    /**
     * 加载url图片
     *
     * @param imageView
     * @param resIcon
     */
    public void loadImage(Activity context,ImageView imageView, int resIcon) {
        if(context!=null&&!context.isFinishing()){
            Glide.with(context)
                    .load(resIcon)
                    .apply(new RequestOptions().centerCrop())
                    .into(imageView);
        }

    }

    /**
     * 根据设置尺寸加载url
     * @param imageView
     * @param resIcon
     * @param width
     * @param height
     */
    public void loadImage(Activity context,ImageView imageView, int resIcon, int width, int height) {
        if(context!=null&&!context.isFinishing()){
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .override(width,height);
            Glide.with(context)
                    .load(resIcon)
                    .apply(options)
                    .into(imageView);
        }

    }

    /**
     * 带尺寸切角
     * @param imageView
     * @param resIcon
     * @param width
     * @param height
     * @param dp
     */
    public void loadImage(Activity context,ImageView imageView, int resIcon, int width, int height, int dp) {
        if(context!=null&&!context.isFinishing()){
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .transform(new RoundedCorners(dp2px(context,dp)))
                    .override(width,height);
            Glide.with(context)
                    .load(resIcon)
                    .apply(options)
                    .into(imageView);
        }

    }

    /**
     * 不带尺寸切角
     * @param imageView
     * @param resIcon
     * @param dp
     */
    public void loadImage(Activity context,ImageView imageView, int resIcon, int dp) {
        if(context!=null&&!context.isFinishing()){
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .transform(new RoundedCorners(dp2px(context,dp)));
            Glide.with(context)
                    .load(resIcon)
                    .apply(options)
                    .into(imageView);
        }

    }

    /**
     * 不带尺寸切角
     */
    public void loadGifImage(Context context, Drawable url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop();
        Glide.with(context)
                .asGif()
                .load(url)
                .apply(options)
                .into(imageView);
    }
    /**
     * 加载圆形图片
     *
     * @param imageView
     * @param resIcon
     */
    public void loadCircleImage(Context context,ImageView imageView, int resIcon) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCrop();   //4.0版本圆形
        Glide.with(context)
                .load(resIcon)
                .apply(options)
                .into(imageView);
    }
    /**
     * 带尺寸加载圆形图片
     *
     * @param imageView
     * @param resIcon
     */
    public void loadCircleImage(Context context,ImageView imageView, int resIcon, int width, int height) {
        RequestOptions options
                = new RequestOptions()
                .centerCrop()
                .circleCrop()
                .override(width,height);
        Glide.with(context)
                .load(resIcon)
                .apply(options)
                .into(imageView);
    }




    //验证图片地址
   /* public GlideUrl getGlideUrl(String url) {
        //Authorization 请求头信息
        LazyHeaders headers = new LazyHeaders.Builder()
                .addHeader("Authorization", "http://cdn.dotasell.com")
                .build();
        return new GlideUrl(url, headers);
    }*/
    public static int dp2px( Context context,float dp)
    {
        final float scale =context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f); //Then add 0.5f to round the figure up to the nearest whole number, when converting to an integer
    }


}
