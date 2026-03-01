package org.ies.wargame.domain.model

import com.google.firebase.firestore.DocumentId

data class ActivityItem(
    @DocumentId
    val id: String,
    val title: String,
    val description: String,
    val expanded: Boolean = false
){
    // Constructor vacío necesario para la deserialización
    constructor() : this(id = "", title = "", description = "", expanded = false )
}