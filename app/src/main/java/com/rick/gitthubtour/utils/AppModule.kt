package com.rick.githubtour.utils

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.rick.githubtour.api.GithubApiService
import com.rick.githubtour.repository.githubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideGithubUserRepository(githubApiService: GithubApiService): githubRepository {
        return githubRepository(githubApiService)
    }

    class LogJsonInterceptor : Interceptor {
        @Throws(IOException::class)

        override fun intercept(chain: Interceptor.Chain): Response {
            val request: Request = chain.request()
            val response: Response = chain.proceed(request)
            val rawJson: String? = response.body()?.string()

            Log.d(
                "TAG",
                String.format(
                    "call: ${
                        response.request().url()
                    } \n  , RetrofitManager raw JSON response is: %s",
                    rawJson
                )
            )

            // Re-create the response before returning it because body can be read only once
            return response.newBuilder()
                .body(ResponseBody.create(response.body()?.contentType(), rawJson.toString()))
                .build()
        }
    }

    @Provides
    @Singleton
    fun provideLogJsonInterceptor(): LogJsonInterceptor {

        return LogJsonInterceptor()

    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: LogJsonInterceptor): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()

    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())//GsonConverterFactory將Json轉換成java/kotlin物件
            .client(okHttpClient)
            .build()

    }


    @Provides
    @Singleton
    fun provideGithubApiService(retrofit: Retrofit): GithubApiService {
        return retrofit.create(GithubApiService::class.java)
    }

}
