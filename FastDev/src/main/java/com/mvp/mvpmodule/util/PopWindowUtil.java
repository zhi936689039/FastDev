package com.mvp.mvpmodule.util;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.mvp.mvpmodule.R;


/**
 * @author 创建人 ：ouyangzhibao
 * @version 1.0
 * @package 包名 ：com.renrenda.client.util
 * @createTime 创建时间 ：2019/8/8
 * @modifyBy 修改人 ：ouyangzhibao
 * @modifyTime 修改时间 ：2019/8/8
 * @modifyMemo 修改备注：
 */
public class PopWindowUtil {
    private View view;
    private  PopupWindow   mPopupWindow;
    private int layou_id;
    private Activity mContext;
    private boolean isShowAsView;//是否在某个View下面显示
    private boolean isMatchParent;//宽高是否是Match_Parent
    public PopWindowUtil(int layou_id,Activity mContext,boolean isShowAsView,boolean isMatchParent){
        view=LayoutInflater.from(mContext).inflate(layou_id, null);
        setView(view);
        this.mContext=mContext;
        this.isShowAsView=isShowAsView;
        this.isMatchParent=isMatchParent;
        mPopupWindow = new PopupWindow(mContext);
        setmPopupWindow(mPopupWindow);
    }
    public void showPop(View dropDownView) {
        // 设置布局文件
        mPopupWindow.setContentView(view);
        // 为了避免部分机型不显示，我们需要重新设置一下宽高
        if(!isMatchParent){
            mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        }else{
            mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            mPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        }
        // 设置pop透明效果
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x0000));
        // 设置pop出入动画
        if(isShowAsView){
            mPopupWindow.setAnimationStyle(R.style.pop_add);
        }
        // 设置pop获取焦点，如果为false点击返回按钮会退出当前Activity，如果pop中有Editor的话，focusable必须要为true
        mPopupWindow.setFocusable(true);

        // 设置点击pop外侧消失，默认为false；在focusable为true时点击外侧始终消失
        // 设置pop可点击，为false点击事件无效，默认为true
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        // 相对于 + 号正下面，同时可以设置偏移量
        if(isShowAsView){
            mPopupWindow.showAsDropDown(dropDownView, -100, -12);
        }else{
            mPopupWindow.showAtLocation(dropDownView, Gravity.TOP, 0, 0);
        }
    }


    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public PopupWindow getmPopupWindow() {
        return mPopupWindow;
    }

    public void setmPopupWindow(PopupWindow mPopupWindow) {
        this.mPopupWindow = mPopupWindow;
    }
}
