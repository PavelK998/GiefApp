package ru.pakarp.GiefptAbitur.data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import ru.pakarp.GiefptAbitur.domain.repository.AuthRepository
import ru.pakarp.GiefptAbitur.domain.model.ResultModel
import ru.pakarp.GiefptAbitur.domain.model.UserModel
import ru.pakarp.GiefptAbitur.utils.Constants
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
) : AuthRepository {


    override suspend fun signIn(user: UserModel): Flow<ResultModel> {
        return flow {
            var isSuccess = false

            emit(ResultModel(result = "loading"))
            try {

                firebaseAuth.createUserWithEmailAndPassword(user.login, user.password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            isSuccess = true
                            val firebaseUser = firebaseAuth.currentUser
                            Log.d("FirebaseAuth", "createUserWithEmailAndPassword: success")
                            if (firebaseUser != null) {
                                user.userId = firebaseUser.uid
                                firebaseFirestore
                                    .collection(Constants.USERS)
                                    .document(firebaseUser.uid)
                                    .set(user)
                            }

                        } else {
                            isSuccess = false
                            Log.d("FirebaseAuth", "createUserWithEmailAndPassword: fault")
                        }

                    }.await()
                if (isSuccess) {
                    emit(ResultModel(result = "success"))
                } else {
                    emit(ResultModel(result = "fault"))
                }
            } catch (e: Exception) {
                emit(ResultModel(e.localizedMessage ?: "Oh, something went wrong!"))
                Log.d(
                    "FirebaseAuth",
                    "createUserWithEmailAndPassword: ${e.localizedMessage ?: "Oh, something went wrong!"}"
                )
            }
        }

    }

    override suspend fun logIn(email: String, password: String): Flow<ResultModel> {
        return flow {
            var isSuccess = false

            emit(ResultModel(result = "loading"))
            try {


                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            isSuccess = true
                            val firebaseUser = firebaseAuth.currentUser
                            Log.d("FirebaseAuth", "signInWithEmailAndPassword: success")
                        } else {
                            isSuccess = false
                            Log.d("FirebaseAuth", "signInWithEmailAndPassword: fault")
                        }

                    }.await()
                if (isSuccess) {
                    emit(ResultModel(result = "success"))
                } else {
                    emit(ResultModel(result = "fault"))
                }
            } catch (e: Exception) {
                emit(ResultModel(e.localizedMessage ?: "Oh, something went wrong!"))
                Log.d(
                    "FirebaseAuth",
                    "createUserWithEmailAndPassword: ${e.localizedMessage ?: "Oh, something went wrong!"}"
                )
            }
        }
    }
}


