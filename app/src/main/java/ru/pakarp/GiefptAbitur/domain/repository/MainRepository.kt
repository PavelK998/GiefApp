package ru.pakarp.GiefptAbitur.domain.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import ru.pakarp.GiefptAbitur.domain.model.LazyColumnItem
import ru.pakarp.GiefptAbitur.domain.model.RowOfDirections
import ru.pakarp.GiefptAbitur.domain.model.UserModel

interface MainRepository {

    suspend fun getAllData(): MutableList<RowOfDirections>

    suspend fun getColumnText(pathName: String): MutableList<LazyColumnItem>

    suspend fun getUserInfo (pathName: String): UserModel
}