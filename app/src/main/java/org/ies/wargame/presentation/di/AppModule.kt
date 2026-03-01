package org.ies.wargame.presentation.di

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import org.ies.wargame.data.repositories.ActivityFirestoreRepository
import org.ies.wargame.data.repositories.AuthFirestoreRepository
import org.ies.wargame.domain.usecase.AddActivityUseCase
import org.ies.wargame.domain.usecase.DeleteActivityUseCase
import org.ies.wargame.domain.usecase.ListActivitiesUseCase
import org.ies.wargame.domain.usecase.LoginUseCase
import org.ies.wargame.domain.usecase.RegisterUseCase
import org.ies.wargame.domain.usecase.UpdateActivityUseCase
import org.ies.wargame.presentation.viewmodel.ActivitiesViewModel
import org.ies.wargame.presentation.viewmodel.AddActivityViewModel
import org.ies.wargame.presentation.viewmodel.EditActivityViewModel
import org.ies.wargame.presentation.viewmodel.LoginViewModel
import org.ies.wargame.presentation.viewmodel.RegisterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    // Singleton del FirebaseFirestore
    single { FirebaseFirestore.getInstance() }
    single { Firebase.auth }
    single { Firebase.firestore }
    // Singleton del respositorio de usuarios, se le inyecta el FirebaseFirestore creado en la sección anterior
    single { ActivityFirestoreRepository(get()) }
    single { AuthFirestoreRepository(get()) }
    // Usamos factory para que proporcione una instancia del UseCase cada vez que se solicite
    factory { AddActivityUseCase(get()) }
    // Usamos factory para que proporcione una instancia del UseCase cada vez que se solicite
    factory { DeleteActivityUseCase(get()) }
    factory { ListActivitiesUseCase(get()) }
    factory { UpdateActivityUseCase(get()) }
    factory { LoginUseCase(get()) }
    factory { RegisterUseCase(get()) }
    // Crea el viewModel con las dependencias que tenga definidas
    viewModel { ActivitiesViewModel(get(), get(), get(), get()) }
    viewModel { AddActivityViewModel(get()) }
    viewModel { EditActivityViewModel(get(),get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
}