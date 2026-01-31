package org.ies.wargame.domain.model

data class ActivityItem(
    val id: Int,
    val title: String,
    val description: String,
    val expanded: Boolean = false
)