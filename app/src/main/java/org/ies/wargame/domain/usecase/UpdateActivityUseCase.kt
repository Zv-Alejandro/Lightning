package org.ies.wargame.domain.usecase

import org.ies.wargame.data.repositories.ActivityFirestoreRepository
import org.ies.wargame.domain.model.ActivityItem

class UpdateActivityUseCase (private val activityRepository: ActivityFirestoreRepository) {
    suspend operator fun invoke(activity: ActivityItem): Boolean {
        // Lógica del caso de uso
        return activityRepository.update(activity);
    }
}