package com.rick.githubtour.api

import com.rick.githubtour.model.UserInfo
import com.rick.githubtour.model.UserListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {

    @Headers("accept: application/json")
    @GET("/users")
    suspend fun getUsersList(
        @Query("since") since: Int,
        @Query("per_page") page: Int?,
    ): Response<List<UserListResponse>>

    @Headers("accept: application/json")
    @GET("/users/{login}")
    suspend fun getUserInfo(
        @Path("login") name: String
    ): Response<UserInfo>
}