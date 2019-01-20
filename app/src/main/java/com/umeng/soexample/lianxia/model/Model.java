package com.umeng.soexample.lianxia.model;

import com.umeng.soexample.lianxia.view.callback.MyCallBack;

/**
 * Created by android_lhf：2019/1/19
 */
public interface Model {
    void setGetData(String url, Class clas, MyCallBack callBack);
}
