package com.picpay.desafio.android

import com.picpay.desafio.android.models.User
import com.picpay.desafio.android.services.PicPayService

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}