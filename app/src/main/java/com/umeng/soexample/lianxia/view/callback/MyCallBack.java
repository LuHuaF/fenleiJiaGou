package com.umeng.soexample.lianxia.view.callback;

/**
 * Created by android_lhf：2019/1/19
 */
public interface MyCallBack<T> {
    void success(T data);

    void failure(T error);
}
