package eu.baran.pokedex.data

import org.koin.dsl.module

internal val dataModule = module {
    single<PokedexRepo> {
        PokedexRepoImpl()
    }
    includes()
}
