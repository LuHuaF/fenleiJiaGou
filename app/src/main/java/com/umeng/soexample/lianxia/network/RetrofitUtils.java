package com.umeng.soexample.lianxia.network;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by android_lhf：2019/1/19
 */
public class RetrofitUtils {
    private static final String BASE_URL = "http://www.zhaoapi.cn/product/";
    private static RetrofitUtils mRetrofitUtils;

    public static synchronized RetrofitUtils getInstence() {
        if (mRetrofitUtils == null) {
            mRetrofitUtils = new RetrofitUtils();
        }
        return mRetrofitUtils;
    }

    MyApiService mApiService;

    private RetrofitUtils() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(interceptor);
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(15, TimeUnit.SECONDS);
        builder.writeTimeout(15, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);

        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mApiService = retrofit.create(MyApiService.class);

    }

    public RetrofitUtils get(String url, HttpListener httpListener) {
        mApiService.get(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(httpListener));
        return mRetrofitUtils;
    }

    public Observer getObserver(final HttpListener httpListener) {
        Observer<ResponseBody> observer = new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String s = responseBody.string();
                    if (httpListener != null) {
                        httpListener.success(s);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (httpListener != null) {
                        httpListener.failure(e.getMessage());
                    }
                }
            }
        };
        return observer;
    }

    public interface HttpListener {
        void success(String data);

        void failure(String error);
    }

}
