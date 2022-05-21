package com.example.realnews

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface retrofitAPI {
    @GET
    fun getNewsbycategory(@Url str: String?): Call<Newsmodel>

    @GET
    fun getallNews(@Url str: String?): Call<Newsmodel>
}