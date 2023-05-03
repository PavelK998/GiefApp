package ru.pakarp.GiefptAbitur.domain.model

data class UserModel(
    var userId: String? = null,
    val login: String,
    val password: String,
    val name: String,
    val surName: String
)

data class ResultModel (
    var result:String
)



