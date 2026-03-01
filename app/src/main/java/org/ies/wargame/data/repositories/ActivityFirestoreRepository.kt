package org.ies.wargame.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import org.ies.wargame.domain.model.ActivityItem

class ActivityFirestoreRepository(val firestore: FirebaseFirestore ) {

    private val activityCollection = firestore.collection("activities")

    // Obtener una actividad por ID
    suspend fun getById(id: String): ActivityItem? {
        return try {
            val documentSnapshot = activityCollection.document(id).get().await()
            documentSnapshot.toObject(ActivityItem::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun list(): Flow<List<ActivityItem>> {
        // Esta implementación crea un Flow que actualiza la lista de usuarios
        // cada vez que hay un cambio en la base de datos
        return queryForList(
            activityCollection,
            ActivityItem::class.java
        )
    }

    // Agregar una nueva actividad
    suspend fun add(activity: ActivityItem): Boolean {
        return try {
            activityCollection.add(activity).await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun update(activity: ActivityItem): Boolean {
        return try {
            activityCollection.document(activity.id).update(
                "title", activity.title,
                "description", activity.description,
                "expanded", activity.expanded,
            ).await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }


    // Eliminar una actividad por ID
    suspend fun delete(id: String): Boolean {
        return try {
            activityCollection.document(id).delete().await()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

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