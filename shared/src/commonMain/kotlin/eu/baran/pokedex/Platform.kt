package eu.baran.pokedex

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform