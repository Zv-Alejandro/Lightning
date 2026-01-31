package org.ies.wargame.presentation.ui.state
data class ActivityItemUi(
    val id: Int,
    val title: String,
    val description: String,
    val expanded: Boolean = false
)

data class ActivitiesUiState(
    val activities: List<ActivityItemUi> = emptyList()
)
