package org.ies.wargame.presentation.ui.state

data class AddActivityUiState(
    val title: String = "",
    val description: String = "",
    val titleError: String = "",
    val descriptionError: String = ""
)
