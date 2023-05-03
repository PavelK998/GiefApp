package ru.pakarp.GiefptAbitur.presentation.useCase

import ru.pakarp.GiefptAbitur.domain.repository.AuthRepository
import ru.pakarp.GiefptAbitur.domain.model.UserModel
import javax.inject.Inject


class AuthUseCase @Inject constructor(private val  repository: AuthRepository) {

    suspend fun registerNewUser(userModel: UserModel) = repository.signIn(user = userModel)

    suspend fun logIn (email: String, password: String) = repository.logIn(email = email, password = password)


}