package com.eduardojr.app_taxis_cliente.models

import com.beust.klaxon.*

private val klaxon = Klaxon()

data class Client (
    val id: String?=null,
    val name: String?=null,
    val lastName: String?=null,
    val email: String?=null,
    val phone: String?=null,
    val image: String?=null
) {



    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<Client>(json)
    }
}