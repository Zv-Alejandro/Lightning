package org.ies.wargame.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.ies.wargame.data.repositories.ActivityFirestoreRepository
import org.ies.wargame.domain.model.ActivityItem

class ListActivitiesUseCase(private val activityRepository: ActivityFirestoreRepository) {
    operator fun invoke(): Flow<List<ActivityItem>> {
        // Lógica del caso de uso
        return activityRepository.list()
    }
}