package org.ies.wargame.data.repositories

import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthFirestoreRepository(private val auth: FirebaseAuth) {
    suspend fun login(email: String, pass: String): Boolean {
        return try {
            auth.signInWithEmailAndPassword(email, pass).await() 
            true
        } catch (e: Exception) {
            e.printStackTrace() 
            false
        }
    }

    suspend fun register(email: String, pass: String): Boolean {
        return try {
            auth.createUserWithEmailAndPassword(email, pass).await() 
            true
        } catch (e: Exception) {
            e.printStackTrace() 
            false
        }
    }

    fun getCurrentUser() = auth.currentUser

    // Este método es siempre igual para cualquier repository
    private fun <T> queryForList(query: Query, clazz: Class<T>): Flow<List<T>> {
        return callbackFlow {

            val listener = query
                .addSnapshotListener { snapshots, error ->
                    if (error != null) {
                        close(error)
                        return@addSnapshotListener
                    }

                    val items = snapshots?.documents?.mapNotNull { doc ->
                        doc.toObject(clazz)

                    } ?: emptyList()

                    trySend(items)
                }

            awaitClose() { listener.remove() }
        }
    }

    // Este método es siempre igual para cualquier repository
    private fun <T> queryForSingle(query: Query, clazz: Class<T>): Flow<T?> {
        return callbackFlow {
            val listener = query
                .addSnapshotListener { snapshots, error ->
                    if (error != null) {
                        close(error)
                        return@addSnapshotListener
                    }

                    val item = snapshots?.documents?.firstOrNull()?.toObject(clazz)

                    trySend(item)
                }
            awaitClose() { listener.remove() }
        }
    }
}