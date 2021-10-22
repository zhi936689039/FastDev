package com.mvp.mvpmodule.base;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.gm.smart_refresh_classics.layout.SmartRefreshLayout;
import com.mvp.mvpmodule.util.DialogUtil;
import com.mvp.mvpmodule.util.ValidateUtils;
import com.mvp.mvpmodule.util.statusBar.XStatusBarHelper;
import org.greenrobot.eventbus.EventBus;
import butterknife.ButterKnife;
import butterknife.Unbinder;
public abstract class BaseListActivity <P extends IBasePresent> extends AppCompatActivity implements RefreshLayoutBaseView {
    protected P mPresent;
    private Context mContext;
    private Dialog mLoadingDialog;
    private Dialog mNormalLoadingDialog;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (!isTaskRoot()) {
//            Intent intent = getIntent();
//            String action = intent.getAction();
//            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && action != null && action.equals(Intent.ACTION_MAIN)) {
//                finish();
//                return;
//            }
//            return;
//        }
        setContentView(getLayoutId());
        XStatusBarHelper.immersiveStatusBar(this);
        unbinder= ButterKnife.bind(this);
        mContext=this;
        if (isUseEventBus()) {
            EventBus.getDefault().register(this);
        }
        //初始化mPresenter
        initPresenter();
        //绑定view
        if(ValidateUtils.isValidate(mPresent)){
            mPresent.attachView(this);
            mPresent.onCreate();
        }
        init();
        initEvent();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(ValidateUtils.isValidate(mPresent)){
            mPresent.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(ValidateUtils.isValidate(mPresent)){
            mPresent.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(ValidateUtils.isValidate(mPresent)){
            mPresent.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(ValidateUtils.isValidate(mPresent)){
            mPresent.onDestroy();
        }
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
        hideLoading();
        if(ValidateUtils.isValidate(unbinder)){
            unbinder.unbind();
        }

    }

    protected abstract void initPresenter();
    protected abstract void init();
    protected abstract void initEvent();
    protected abstract int getLayoutId();
    protected abstract boolean isUseEventBus();
    @Override
    public void showLoading(boolean isCancler, String message, boolean isDealDialogDismiss, DialogUtil.LoadingDialogListener loadingDialogListener) {
        if(isDealDialogDismiss){
            mLoadingDialog= DialogUtil.showLoadingDialog(mContext,message, isCancler, isDealDialogDismiss, loadingDialogListener);
        }else{
            mLoadingDialog= DialogUtil.showLoadingDialog(mContext,message, isCancler, isDealDialogDismiss,null);
        }
    }

    @Override
    public void showLoading(Context context) {
        mNormalLoadingDialog= DialogUtil.showLoading(context);
    }

    @Override
    public void hideLoading() {
        if(ValidateUtils.isValidate(mLoadingDialog)&&mLoadingDialog.isShowing()){
            mLoadingDialog.dismiss();
        }
        if(ValidateUtils.isValidate(mNormalLoadingDialog)&&mNormalLoadingDialog.isShowing()){
            mNormalLoadingDialog.dismiss();
        }
    }

    protected Context getContext(){
        return mContext;
    }
    protected Activity getActivity(){
        return BaseListActivity.this;
    }

    protected void startActivity(Class<?> targetClass) {
        Intent intent = new Intent(this, targetClass);
        startActivity(intent);
    }

    protected void startActivity(Class<?> targetClass, String key, String value) {
        Intent intent = new Intent(this, targetClass);
        intent.putExtra(key, value);
        startActivity(intent);
    }

    protected void startActivity(Class<?> targetClass, int requestCode) {
        Intent intent = new Intent(this, targetClass);
        startActivityForResult(intent, requestCode);
    }

    protected void startActivity(Class<?> targetClass, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, targetClass);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public SmartRefreshLayout getRefreshLayout() {
       return setRefreshLayout();
    }
    protected abstract SmartRefreshLayout setRefreshLayout();
}
