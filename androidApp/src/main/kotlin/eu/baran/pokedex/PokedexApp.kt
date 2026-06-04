package eu.baran.pokedex

import android.app.Application
import org.koin.android.ext.koin.androidContext

class PokedexApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@PokedexApp)
        }
    }
}