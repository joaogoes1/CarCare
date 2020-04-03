package br.com.joaogoes.carcare.di

import br.com.joaogoes.data.datasource.LocalDataSource
import br.com.joaogoes.database.mapper.RevisionItemMapper
import br.com.joaogoes.data.repository.revision.AppRevisionRepository
import br.com.joaogoes.data.repository.revision.RevisionRepository
import br.com.joaogoes.data.usecase.GetRevisionItemsUseCase
import br.com.joaogoes.data.usecase.SaveRevisionItemUseCase
import br.com.joaogoes.database.AppDatabase
import br.com.joaogoes.database.datasource.AppLocalDataSource
import br.com.joaogoes.ui.home.HomeViewModel
import br.com.joaogoes.usecases.GetRevisionItems
import br.com.joaogoes.usecases.SaveRevisionItem
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { RevisionItemMapper() }
    single { get<AppDatabase>().revisionItemDao() }
    single { AppDatabase.createDatabase(get()) }
    single<LocalDataSource> { AppLocalDataSource(get(), get()) }
    single<RevisionRepository> { AppRevisionRepository(get()) }
    single<GetRevisionItemsUseCase> { GetRevisionItems(get()) }
    single<SaveRevisionItemUseCase> { SaveRevisionItem(get()) }
    viewModel { HomeViewModel(get(), get()) }
}
