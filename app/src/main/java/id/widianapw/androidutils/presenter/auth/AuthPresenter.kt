package id.widianapw.androidutils.presenter.auth

import id.widianapw.androidutils.model.auth.AuthRequest
import id.widianapw.androidutils.model.auth.AuthResponse
import id.widianapw.androidutils.presenter.base.BaseDelegate
import id.widianapw.androidutils.utilities.network.client
import id.widianapw.androidutils.utilities.network.requestApi
import io.ktor.client.request.*

/**
 * Created by Widiana Putra on 10/11/21
 * Copyright (c) PT. TIMEDOOR INDONESIA
 */

interface AuthDelegate : BaseDelegate {
    fun loginSuccess(response: AuthResponse) {}
}

class AuthPresenter(private val authDelegate: AuthDelegate) {
    suspend fun login(email: String, password: String) {
        requestApi(authDelegate) {
            val response =
                client.post<AuthResponse>(path = "login") {
                    body = AuthRequest(email, password)
                }
            authDelegate.loginSuccess(response)
        }
    }
}


