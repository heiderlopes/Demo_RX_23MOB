package br.com.heiderlopes.demorx.api

import br.com.heiderlopes.demorx.model.Pokemon
import br.com.heiderlopes.demorx.model.PokemonResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonAPI {

    @GET("/api/v2/pokemon")
    fun listaPokemons(): Observable<PokemonResponse>

    @GET("/api/v2/pokemon/{nomePokemon}")
    fun buscaPor(@Path("nomePokemon") nomePokemon: String)
            : Observable<Pokemon>

}