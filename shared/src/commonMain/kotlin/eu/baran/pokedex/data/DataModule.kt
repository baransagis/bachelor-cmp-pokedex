package eu.baran.pokedex.data

import eu.baran.pokedex.data.remote.remoteModule
import org.koin.dsl.module

internal val dataModule = module {
    single<PokedexRepo> {
        PokedexRepoImpl(get())
    }
    includes(remoteModule)
}
