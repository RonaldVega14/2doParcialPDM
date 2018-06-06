package com.vega.gamenews.API;

import com.vega.gamenews.Models.Login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GamesAPI {

    static final String END_POINT = "http://gamenewsuca.herokuapp.com";

    @FormUrlEncoded
    @POST("/login")
    Call<Login> login(
            @Field("user") String Username,
            @Field("password") String password
    );

}
