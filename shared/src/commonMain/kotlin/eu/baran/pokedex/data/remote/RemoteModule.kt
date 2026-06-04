package eu.baran.pokedex.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val remoteModule = module {
    single { getHttpClient()}
    singleOf(::PokedexApi)
}
 private fun getHttpClient(): HttpClient =
     HttpClient {
         expectSuccess = true

         install(ContentNegotiation) {
             json(
                 Json {
                     ignoreUnknownKeys = true
                     isLenient = true
                 }
             )
         }

         install(HttpTimeout) {
             requestTimeoutMillis = 15_000
             connectTimeoutMillis = 15_000
             socketTimeoutMillis = 15_000
         }

         defaultRequest {
             contentType(ContentType.Application.Json)
             accept(ContentType.Application.Json)
         }
 }