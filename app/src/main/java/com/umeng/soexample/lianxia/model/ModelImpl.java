package com.umeng.soexample.lianxia.model;

import com.google.gson.Gson;
import com.umeng.soexample.lianxia.network.RetrofitUtils;
import com.umeng.soexample.lianxia.view.callback.MyCallBack;

/**
 * Created by android_lhf：2019/1/19
 */
public class ModelImpl implements Model {
    @Override
    public void setGetData(String url, final Class clas, final MyCallBack callBack) {
        RetrofitUtils.getInstence().get(url, new RetrofitUtils.HttpListener() {
            @Override
            public void success(String data) {
                Object o = new Gson().fromJson(data, clas);
                callBack.success(o);
            }

            @Override
            public void failure(String error) {
                callBack.failure(error);
            }
        });
    }
}
