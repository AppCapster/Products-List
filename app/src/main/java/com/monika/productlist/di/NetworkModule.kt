package com.monika.productlist.di

import com.monika.productlist.data.remote.api.ProductApi
import com.monika.productlist.data.repository.ProductApiRepository
import com.monika.productlist.data.repositoryimpl.ProductApiRepositoryImpl
import com.monika.productlist.util.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideBaseUrl(): String = BASE_URL

    @Provides
    @Singleton
    fun provideOKHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(1200, TimeUnit.SECONDS)
            .connectTimeout(1200, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): MoshiConverterFactory {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Singleton
    fun getRetrofit(
        baseUrl: String,
        moshi: MoshiConverterFactory,
        httpClient: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(moshi)
            .client(httpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ProductApi = retrofit.create(ProductApi::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(userApiRepositoryImplementation: ProductApiRepositoryImpl): ProductApiRepository =
        userApiRepositoryImplementation

}