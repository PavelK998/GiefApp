package ru.pakarp.GiefptAbitur.presentation.useCase

import ru.pakarp.GiefptAbitur.domain.repository.MainRepository
import javax.inject.Inject

class MainUseCAse @Inject constructor(private val repository:MainRepository) {

    suspend fun getAllData() = repository.getAllData()

    suspend fun getColumnText(pathName: String) = repository.getColumnText(pathName = pathName)

    suspend fun getUserInfo(pathName: String)= repository.getUserInfo(pathName = pathName)
}