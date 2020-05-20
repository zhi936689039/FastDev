package com.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * @author 创建人 ：ouyangSuperHandsome
 * @version 1.0
 * @package 包名 ：com.util
 * @createTime 创建时间 ：2020/5/19 21:15
 * @modifyBy 修改人 ：ouyangSuperHandsome
 * @modifyTime 修改时间 ：2020/5/19 21:15
 * @modifyMemo 修改备注：
 */
public class DialogUtil {

    /**
     * 菊花loading对话框
     * @param context
     * @param message      等待时加载的文字
     * @param isCancelable         是否可以点击外部退出dialog
     * @param isDealDialogDismiss        是否处理dialog消失后的操作
     * @param loadingDialogListener      loadingDiloag的接口
     */
    public static Dialog showLoadingDialog(Context context, String message, boolean isCancelable,boolean isDealDialogDismiss,LoadingDialogListener loadingDialogListener) {
        Dialog loadingDialog=null;
        View dialogLayout;
        if (loadingDialog == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            // 得到加载view
            dialogLayout = inflater.inflate(R.layout.layout_view_loading, null);
            TextView loading_tv = dialogLayout.findViewById(R.id.loading_tv);
            loadingDialog = new ProgressDialog(context, R.style.LoadingDialog);
            loadingDialog.setCancelable(isCancelable);
            loadingDialog.setCanceledOnTouchOutside(isCancelable);
            loadingDialog.show();
            loadingDialog.setContentView(dialogLayout);// 设置布局 需要在show后执行 否则报requestFeature() must be called before adding content
            //loadingDialog.setOnDismissListener(dialog -> onLoadingDismiss());
            loadingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    if(isDealDialogDismiss&&null!=loadingDialogListener){
                        loadingDialogListener.onLoadingDismiss();
                    }
                }
            });
            loading_tv.setText(message);
        }
        if (context!= null && !((Activity)context).isFinishing() &&!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
        return loadingDialog;
    }
    public interface LoadingDialogListener{
        void onLoadingDismiss();
    }
}
