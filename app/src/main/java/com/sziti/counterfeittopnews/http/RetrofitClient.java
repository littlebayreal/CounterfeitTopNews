package com.sziti.counterfeittopnews.http;

import android.support.annotation.Nullable;
import android.util.Log;

import com.sziti.counterfeittopnews.Cons;
import com.sziti.counterfeittopnews.MyApp;
import com.sziti.counterfeittopnews.util.NetWorkUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String TAG = RetrofitClient.class.getSimpleName();
    public static RetrofitClient mApiRetrofit;
    public static APIService apiService;
    private Interceptor mCacheInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            CacheControl.Builder cacheBuilder = new CacheControl.Builder();
            cacheBuilder.maxAge(0, TimeUnit.SECONDS);
            cacheBuilder.maxStale(365, TimeUnit.DAYS);
            CacheControl cacheControl = cacheBuilder.build();

            Request request = chain.request();
            if (!NetWorkUtils.isNetworkAvailable(MyApp.getContext())) {
                request = request.newBuilder()
                        .cacheControl(cacheControl)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            //如果在网络可用的情况下 直接访问网络获取最新数据
            if (NetWorkUtils.isNetworkAvailable(MyApp.getContext())) {
                int maxAge = 0; // read from cache
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            } else {
                //如果在网络不可用的情况下  直接读取本地4个星期内的缓存
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };
    /**请求访问quest和response拦截器*/
    private Interceptor mLogInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long startTime = System.currentTimeMillis();
            okhttp3.Response response = chain.proceed(chain.request());
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            Log.e(TAG,"----------Request Start----------------");
            Log.e(TAG,"| " + request.toString());
            Log.e(TAG,"| Response:" + content);
            Log.e(TAG,"----------Request End:" + duration + "毫秒----------");
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();
        }
    };
    //初始化retrofit
    public RetrofitClient(){
        if (apiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Cons.BASE_SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(getHttpClient())
                    .build();
            apiService = retrofit.create(APIService.class);
        }
    }
    public OkHttpClient getHttpClient() {
		HttpLoggingInterceptor logInterceptor =
			new HttpLoggingInterceptor(new HttpLog()).setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(logInterceptor)
                .build();
        return client;
    }

    public static RetrofitClient getInstance() {
        if (mApiRetrofit == null) {
            synchronized (Object.class) {
                if (mApiRetrofit == null) {
                    mApiRetrofit = new RetrofitClient();
                }
            }
        }
        return mApiRetrofit;
    }
    public APIService getApiService() {
        return apiService;
    }
}
