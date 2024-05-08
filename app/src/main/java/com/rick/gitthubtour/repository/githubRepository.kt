package com.rick.githubtour.repository

import com.rick.githubtour.api.GithubApiService
import com.rick.githubtour.model.UserInfo
import com.rick.githubtour.model.UserListResponse
import retrofit2.Response


class githubRepository(private val githubApiService: GithubApiService) {

    suspend fun callGithubApiService(since: Int, per_page: Int): Response<List<UserListResponse>> {
        return githubApiService.getUsersList(since, per_page)
    }

    suspend fun callGithubApiServiceInfo(name :String): Response<UserInfo> {
        return githubApiService.getUserInfo(name)
        /*
        return try {
            val response = githubApiService.getUserInfo(name)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            }
            else{
                // 處理 HTTP 錯誤
                Result.failure(RuntimeException("HTTP error ${response.code()}"))
            }
        }catch (e: Exception){
            // 處理連線錯誤或其他錯誤
            Result.failure(e)
        }*/
    }




}