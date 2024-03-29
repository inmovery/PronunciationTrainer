package ru.contesta.pronunciationtrainer.api.service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import ru.contesta.pronunciationtrainer.utils.*;

public class NetworkService {
    private static NetworkService mInstance;
    private Retrofit mRetrofit;

    private NetworkService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder().addInterceptor(interceptor);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(Credentials.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }

        return mInstance;
    }

    public NetworkRequests getRequestsApi() {
        return mRetrofit.create(NetworkRequests.class);
    }

}