package ru.contesta.pronunciationtrainer.api.service;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.contesta.pronunciationtrainer.api.models.*;
import ru.contesta.pronunciationtrainer.api.responses.*;

public interface NetworkRequests {
    @POST("auth")
    Call<AuthorizationResponse> auth(@Body AuthorizationRequest authorizationRequest);

    @GET("register")
    Call<RegistrationResponse> register(
            @Header("Authorization") String authToken,
            @Body RegistrationRequest registrationRequest
    );

    @Headers("Accept: application/json")
    @POST("audio")
    Call<AudioResponse> handleAudio(
            @Header("Authorization") String authToken,
            @Body RequestBody requestBody,
            @Query("word") String word
    );

    @GET("profiles")
    Call<ProfileResponse> getProfile(
            @Header("Authorization") String authToken,
            @Body ProfileRequest profileRequest
    );

    /*
    *
    * TODO: реализовать следующее:
    *   1. Редактирование профиля
    *   2. Отправка статистики
    *   3. Получение результата отправки аудио файла
    *
    * */

}