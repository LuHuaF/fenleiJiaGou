package com.umeng.soexample.lianxia.presenter;

import com.umeng.soexample.lianxia.model.ModelImpl;
import com.umeng.soexample.lianxia.view.IView;
import com.umeng.soexample.lianxia.view.callback.MyCallBack;

/**
 * Created by android_lhf：2019/1/19
 */
public class PresenterImpl implements Presenter {
    private ModelImpl model;
    private IView iView;

    public PresenterImpl(IView iView) {
        this.iView = iView;
        model = new ModelImpl();
    }

    @Override
    public void startGetRequest(String url, Class clas) {
        model.setGetData(url, clas, new MyCallBack() {
            @Override
            public void success(Object data) {
                iView.success(data);
            }

            @Override
            public void failure(Object error) {
                iView.failure(error);
            }
        });
    }
    public void onDestory() {
        if (model != null) {
            model = null;
        }
        if (iView != null) {
            iView = null;
        }
    }
}
