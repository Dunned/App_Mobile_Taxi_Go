package com.eduardojr.app_taxis_cliente.providers

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class AuthProvider {

    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun register(email: String, password:String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }
}