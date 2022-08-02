package com.seif.pagingv3demo.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val characterData: List<CharacterData>
)