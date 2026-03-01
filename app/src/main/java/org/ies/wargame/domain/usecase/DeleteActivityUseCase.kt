package org.ies.wargame.domain.usecase

import org.ies.wargame.data.repositories.ActivityFirestoreRepository
import org.ies.wargame.domain.model.ActivityItem

class DeleteActivityUseCase (private val activityRepository: ActivityFirestoreRepository) {
    suspend operator fun invoke(id : String): Boolean {
        // Lógica del caso de uso
        return activityRepository.delete(id);
    }
}