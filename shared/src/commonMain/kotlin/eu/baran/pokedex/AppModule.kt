package eu.baran.pokedex

import eu.baran.pokedex.data.dataModule
import eu.baran.pokedex.data.local.localModule
import eu.baran.pokedex.ui.screens.detail.DetailViewModel
import eu.baran.pokedex.ui.screens.list.ListViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::ListViewModel)
    viewModelOf(::DetailViewModel)
}
val appModule = module {
    includes(
        dataModule,
        viewModelModule,
        platformModule,
        localModule,
    )
}

fun initKoin(
    config: KoinAppDeclaration? = null
): KoinApplication {
    return startKoin {
        config?.invoke(this)
        modules(appModule)
    }
}