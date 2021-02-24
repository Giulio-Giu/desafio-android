package com.picpay.desafio.android.services

import com.picpay.desafio.android.models.User
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): Call<List<User>>
}