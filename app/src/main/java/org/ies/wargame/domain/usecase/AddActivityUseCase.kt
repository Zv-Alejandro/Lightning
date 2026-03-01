package org.ies.wargame.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.ies.wargame.data.repositories.ActivityFirestoreRepository
import org.ies.wargame.domain.model.ActivityItem

class AddActivityUseCase(private val activityRepository: ActivityFirestoreRepository) {
    suspend operator fun invoke(activity : ActivityItem):  Boolean {
        // Lógica del caso de uso
        return activityRepository.add(activity)
    }
}