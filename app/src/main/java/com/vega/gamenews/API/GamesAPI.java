package com.vega.gamenews.API;

import com.vega.gamenews.Models.Login;
import com.vega.gamenews.Models.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface GamesAPI {

    static final String END_POINT = "http://gamenewsuca.herokuapp.com";

    @FormUrlEncoded
    @POST("/login")
    Call<String> login(
            @Field("user") String Username,
            @Field("password") String password
    );

    @GET("/news")
    Call<List<News>> getNews(@Header("Authorization") String auth);

    @GET("/news/type/list")
    Call<List<String>> getCategories(@Header("Authorization") String auth);


}
