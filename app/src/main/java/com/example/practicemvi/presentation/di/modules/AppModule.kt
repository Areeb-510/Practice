package com.example.practicemvi.presentation.di.modules

import com.example.practicemvi.data.api.ApiService
import com.example.practicemvi.data.repository.ItemRepositoryImpl
import com.example.practicemvi.domain.repository.ItemRepository
import com.example.practicemvi.domain.usecases.GetItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideItemRepository(apiService: ApiService): ItemRepository {
        return ItemRepositoryImpl(apiService)
    }

    @Provides
    fun provideGetItemsUseCase(repository: ItemRepository): GetItemsUseCase {
        return GetItemsUseCase(repository)
    }
}
