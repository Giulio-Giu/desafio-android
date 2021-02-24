package com.picpay.desafio.android.services

import com.picpay.desafio.android.R
import com.picpay.desafio.android.ui.activities.MainActivity
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInitializer(contextActivity: MainActivity) {

    companion object {
        private val okHttpClient: OkHttpClient by lazy {
            OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()
        }
    }

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(contextActivity.resources.getString(R.string.service_baseUrl))
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun servicePicPay(): PicPayService {
        return retrofit.create(PicPayService::class.java)
    }
}