package eu.baran.pokedex

import android.app.Application

class PokedexApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}