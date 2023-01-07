package uz.gita.mobilebankingcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.mobilebankingcompose.data.repository.AuthRepositoryImpl
import uz.gita.mobilebankingcompose.domain.repository.AuthRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun repoModule(impl: AuthRepositoryImpl): AuthRepository
}