package com.umeng.soexample.lianxia.network;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by android_lhf：2019/1/19
 */
public interface MyApiService {
    @GET
    Observable<ResponseBody> get(@Url String url);
}
