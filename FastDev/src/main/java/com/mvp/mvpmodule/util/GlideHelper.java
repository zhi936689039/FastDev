package com.mvp.mvpmodule.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.mvp.mvpmodule.BuildConfig;

/**
 * Created by Frank on 2017/3/31.
 * Glide 4.0 正式版
 */

public enum GlideHelper {
    INSTANCE;


    /**
     * 继续加载图片
     */
    public void resumRequestImg(Context mContext){
        Glide.with(mContext).resumeRequests();
    }
    public void pauseRequestImg(Context mContext){
        Glide.with(mContext).pauseRequests();
    }
    public void loadAvatar(Activity context, ImageView imageView, String url,Drawable defaultAvater) {
        if(context!=null&&!context.isFinishing()){
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy( DiskCacheStrategy.AUTOMATIC)
                    .placeholder(defaultAvater);
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
    public void loadImage(Activity context, ImageView imageView, String url,int res) {
        if(context!=null&&!context.isFinishing()){
            RequestOptions options = new RequestOptions()
                    .placeholder(res)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            LogUtil.e("图片加载","图片加载失败:"+"图片地址:"+url+"----异常:"+e.toString(), BuildConfig.DEBUG);
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

    /*-------------------------- 资源图片 ---------------------------------*/
    /**
     * 加载url图片
     *
     * @param context
     * @param imageView
     * @param resIcon
     */
    public void loadImage(Context context, ImageView imageView, String resIcon,Drawable defaultIcon) {
        RequestOptions options = new RequestOptions()
                .placeholder(defaultIcon)
                .error(defaultIcon)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(context)
                .load(resIcon)
                .apply(options)
                .into(imageView);
    }
}
