package eu.baran.pokedex

import eu.baran.pokedex.data.remote.ApiConfig
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single {
        ApiConfig(
            baseUrl = "http://192.168.188.34:8080"
        )
    }
}
