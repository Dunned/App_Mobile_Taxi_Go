package com.eduardojr.app_taxis_cliente.providers

import com.eduardojr.app_taxis_cliente.models.Client
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ClientProvider {
    val db = Firebase.firestore.collection("Clients")

    fun create(client: Client):Task<Void>{
        return db.document(client.id!!).set(client)
    }



}