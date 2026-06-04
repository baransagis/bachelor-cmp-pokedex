package eu.baran.pokedex

import eu.baran.pokedex.data.dataModule
import eu.baran.pokedex.ui.screens.detail.DetailViewModel
import eu.baran.pokedex.ui.screens.list.ListViewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::ListViewModel)
    viewModelOf(::DetailViewModel)
}

expect val platformModule: Module

val appModule = module {
    includes(
        dataModule,
        viewModelModule,
        platformModule,
    )
}

fun initKoin(configuration: KoinAppDeclaration? = null) {
    startKoin {
        includes(configuration)
        modules(appModule)
        printLogger(Level.DEBUG)
    }}