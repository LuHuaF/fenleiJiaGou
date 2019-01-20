package com.umeng.soexample.lianxia.view;

/**
 * Created by android_lhf：2019/1/19
 */
public interface IView<T> {
    void success(T data);

    void failure(T error);
}
