package com.mvp.mvpmodule.base;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import com.gm.smart_refresh_classics.layout.SmartRefreshLayout;
import com.mvp.mvpmodule.util.DialogUtil;
import com.mvp.mvpmodule.util.ValidateUtils;

import org.greenrobot.eventbus.EventBus;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseListFragmentForSupport<P extends IBasePresent>  extends Fragment implements RefreshLayoutBaseView{
    public static final String IS_HIDDEN = "is_hidden";
    protected P mPresent;
    private Dialog mLoadingDialog;
    private Dialog mNormalLoadingDialog;
    protected Unbinder unbinder;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (savedInstanceState != null) {
//            boolean isHidden = savedInstanceState.getBoolean(IS_HIDDEN);
//            FragmentTransaction ft = getFragmentManager().beginTransaction();
//            if (isHidden) {
//                ft.hide(this);
//            } else {
//                ft.show(this);
//            }
//            ft.commit();
//        }
        // 初始化变量
        initVariables(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(getLayoutId(),container,false);
        unbinder= ButterKnife.bind(this,view);
        if (isUseEventBus()) {
            EventBus.getDefault().register(this);
        }
        initPresenter();
        //绑定view
        if(ValidateUtils.isValidate(mPresent)){
            mPresent.attachView(this);
            mPresent.onCreate();
        }
        init(view);
        loadData();
        return view;
    }
    protected abstract int getLayoutId();
    /**
     * 初始化变量：
     * 1. 初始化成员变量
     * 2. 获取Intent传递的变量
     */
    protected abstract void initVariables(@Nullable Bundle savedInstanceState);

    protected abstract void initPresenter();
    protected abstract void init(View view);
    /**
     * 加载数据
     */
    protected abstract void loadData();
    protected abstract boolean isUseEventBus();

    @Override
    public void showLoading(Context context) {
        mNormalLoadingDialog= DialogUtil.showLoading(context);
    }

    @Override
    public void hideLoading() {
        if(ValidateUtils.isValidate(mLoadingDialog)&&mLoadingDialog.isShowing()&&!getActivity().isFinishing()){
            mLoadingDialog.dismiss();
        }
        if(ValidateUtils.isValidate(mNormalLoadingDialog)&&mNormalLoadingDialog.isShowing()&&!getActivity().isFinishing()){
            mNormalLoadingDialog.dismiss();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if(ValidateUtils.isValidate(mPresent)){
            mPresent.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(ValidateUtils.isValidate(mPresent)){
            mPresent.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(ValidateUtils.isValidate(mPresent)){
            mPresent.onStop();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(ValidateUtils.isValidate(unbinder)){
            unbinder.unbind();
        }
        if(ValidateUtils.isValidate(mPresent)){
            mPresent.onDestroy();
        }
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
        hideLoading();
    }
    protected void startActivity(Class<?> targetClass,boolean isHasAnim) {
        Intent intent = new Intent(getActivity(), targetClass);
        startActivity(intent);
        if(!isHasAnim){
            getActivity().overridePendingTransition(0, 0);
        }
    }

    protected void startActivity(Class<?> targetClass, String key, String value) {
        Intent intent = new Intent(getActivity(), targetClass);
        intent.putExtra(key, value);
        startActivity(intent);

    }

    protected void startActivity(Class<?> targetClass, int requestCode) {
        Intent intent = new Intent(getActivity(), targetClass);
        startActivityForResult(intent, requestCode);
        getActivity().overridePendingTransition(0, 0);

    }

    protected void startActivity(Class<?> targetClass, int requestCode, Bundle bundle) {
        Intent intent = new Intent(getActivity(), targetClass);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }
    @Override
    public SmartRefreshLayout getRefreshLayout() {
       return setRefreshLayout();
    }
    protected abstract SmartRefreshLayout setRefreshLayout();
}
