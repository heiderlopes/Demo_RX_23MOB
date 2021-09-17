package br.com.heiderlopes.demorx.api

import br.com.heiderlopes.demorx.model.Pokemon
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PokemonService {

    val api: PokemonAPI

    init {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()

        api = retrofit.create(PokemonAPI::class.java)
    }

    fun loadPokemons(): Observable<Pokemon> {
        return api.listaPokemons()
            .flatMap { Observable.fromIterable(it.results) }
            .flatMap { api.buscaPor(it.name) }
    }
}