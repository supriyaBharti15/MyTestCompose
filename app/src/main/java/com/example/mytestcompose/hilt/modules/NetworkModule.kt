package com.example.mytestcompose.hilt.modules

import com.example.mytestcompose.api.ApiServices
import com.example.mytestcompose.api.ApiServicesNew
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofitServices():ApiServices{

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build().create(ApiServices::class.java)
    }

    /*@Singleton
    @Provides
    fun provideRetroInstance():ApiServicesNew{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://dummyjson.com/")
            .build().create(ApiServicesNew::class.java)
    }*/
}