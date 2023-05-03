package ru.pakarp.GiefptAbitur.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.pakarp.GiefptAbitur.domain.model.ResultModel
import ru.pakarp.GiefptAbitur.domain.model.UserModel

interface AuthRepository {

    suspend fun signIn(user: UserModel): Flow<ResultModel>

    suspend fun logIn(email: String, password: String): Flow<ResultModel>
}