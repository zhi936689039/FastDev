package com.util;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



/**
 * Created by Frank on 2017/6/13.
 */

public class ToastUtil {

    /**
     * 纯文字自定义toast
     * @param context
     * @param message
     */
    public static void showToast(Context context, String message){
        showToast(context,message,0);
    }


    public static void showWarningToast(Context context, String message){
     //   showToast(context,message, R.mipmap.toast_warning);
    }

    /**
     * 带图片自定义toast
     * @param context
     * @param message
     * @param resIcon
     */
    public static void showToast(Context context, String message, int resIcon){
        Toast toast = new Toast(context);
        View view = View.inflate(context, R.layout.layout_toast_layout,null);
        TextView messageTv = (TextView) view.findViewById(R.id.message_tv);
        ImageView toast_pic_iv = (ImageView) view.findViewById(R.id.toast_pic_iv);
        if(resIcon!=0){
            toast_pic_iv.setVisibility(View.VISIBLE);
            toast_pic_iv.setImageResource(resIcon);
        }
        messageTv.setText(message);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
