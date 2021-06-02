package ru.contesta.pronunciationtrainer.api.service;

import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    @Multipart
    @POST("audio")
    Call<AudioResponse> handleAudio(
            @Part("word") RequestBody word,
            @Part("file\"; filename=\"apple_audio.wav") RequestBody file
    );

    @GET("word")
    Call<WordResponse> getWord();

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
    *   4. Загрузку и генерацию названий изображений на heroku сервер (или на inmovery)
    *
    * */

}