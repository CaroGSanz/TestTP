package com.cgonzalez.totalplayprueba.model.network.retrofit

import com.cgonzalez.totalplayprueba.model.network.response.ResponseData
import com.cgonzalez.totalplayprueba.model.network.response.SessionClientResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("client")
    suspend fun getUserClient(
        @Query("user") user: String,
        @Query("password") password: String
    ): Response<SessionClientResponse>

    @GET("references")
    suspend fun getAllReferences(
        @Query("session") userSession: String,
    ): Response<ResponseData>
}