package br.com.heiderlopes.demorx.model

import com.google.gson.annotations.SerializedName

data class Sprites(
    @SerializedName("front_default")
    val frontDefault: String
)