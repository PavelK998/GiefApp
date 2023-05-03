package ru.pakarp.GiefptAbitur.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.pakarp.GiefptAbitur.data.AuthRepositoryImpl
import ru.pakarp.GiefptAbitur.data.MainRepositoryImpl
import ru.pakarp.GiefptAbitur.domain.repository.AuthRepository
import ru.pakarp.GiefptAbitur.domain.repository.MainRepository
import ru.pakarp.GiefptAbitur.presentation.useCase.AuthUseCase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()

    @Singleton
    @Provides
    fun provideAuthRepository(firebaseAuth: FirebaseAuth,
                              firestore: FirebaseFirestore): AuthRepository =AuthRepositoryImpl(firebaseAuth = firebaseAuth, firebaseFirestore = firestore)

    @Singleton
    @Provides
    fun provideAuthUseCase(authRepository: AuthRepository) = AuthUseCase(repository = authRepository)

    @Singleton
    @Provides
    fun provideMainRepository(firestore:FirebaseFirestore): MainRepository = MainRepositoryImpl(firebaseFirestore = firestore)

}