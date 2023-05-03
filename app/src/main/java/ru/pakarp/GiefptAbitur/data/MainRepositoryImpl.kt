package ru.pakarp.GiefptAbitur.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ru.pakarp.GiefptAbitur.domain.model.LazyColumnItem
import ru.pakarp.GiefptAbitur.domain.model.RowOfDirections
import ru.pakarp.GiefptAbitur.domain.model.UserModel
import ru.pakarp.GiefptAbitur.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
):MainRepository {
    private val TAG = "MainRepositoryImpl"
    override suspend fun getAllData(): MutableList<RowOfDirections> {

        val resultList = mutableListOf<RowOfDirections>()

        firebaseFirestore.collection("RowOfDirections")
            .get()
            .addOnFailureListener {
                Log.d(TAG, "getAllData: ${it.localizedMessage}")
            }

            .addOnSuccessListener { result ->
                for (res in result){
                   val id =  res.data["id"].toString()
                    val name = res.data["name"].toString()
                    resultList.add(RowOfDirections(id = id, name = name, exception = null))
                    Log.d(TAG, "id: $id")
                    Log.d(TAG, "name: $name")
                }

            }.await()

        Log.d(TAG, "getAllData: $resultList")
       return resultList
    }

    override suspend fun getColumnText(pathName: String): MutableList<LazyColumnItem> {
        val resultList = mutableListOf<LazyColumnItem>()

        firebaseFirestore.collection(pathName)
            .get()
            .addOnFailureListener {
                Log.d(TAG, "getAllData: ${it.localizedMessage}")
            }

            .addOnSuccessListener { result ->
                for (res in result){
                    val id =  res.data["id"].toString()
                    val text = res.data["text"].toString()
                    val name = res.data["name"].toString()
                    resultList.add(LazyColumnItem(id = id, name = name, text = text ))
                    Log.d(TAG, "id: $id")
                    Log.d(TAG, "name: $name")
                }

            }.await()

        Log.d("getColumnText", "getAllData: $resultList")
        return resultList
    }

    override suspend fun getUserInfo(pathName: String): UserModel {
        var id = ""
        var login = ""
        var password = ""
        var name = ""
        var surName = ""



        firebaseFirestore.collection("users").document(pathName)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    id = document.data?.get("userId").toString()
                    login = document.data?.get("login").toString()
                    password = document.data?.get("password").toString()
                    name = document.data?.get("name").toString()
                    surName = document.data?.get("surName").toString()
                }

            }.await()


        return UserModel(
            userId = id,
            login = login,
            password = password,
            name = name,
            surName = surName
        )
    }
}