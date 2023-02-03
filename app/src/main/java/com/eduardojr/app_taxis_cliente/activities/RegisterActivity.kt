package com.eduardojr.app_taxis_cliente.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.eduardojr.app_taxis_cliente.databinding.ActivityRegisterBinding
import com.eduardojr.app_taxis_cliente.models.Client
import com.eduardojr.app_taxis_cliente.providers.AuthProvider
import com.eduardojr.app_taxis_cliente.providers.ClientProvider

class RegisterActivity : AppCompatActivity() {


    private lateinit var binding: ActivityRegisterBinding
    
    private val authProvider = AuthProvider()

    private val clientProvider = ClientProvider()
    


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)

        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        binding.btnGoToLogin.setOnClickListener {
            goToLogin()
        }

        binding.btnRegister.setOnClickListener {
            register()
        }

    }


    private fun register(){
        val name = binding.textFieldName.text.toString()
        val lastname = binding.textFieldLastName.text.toString()
        val email = binding.textFieldemail.text.toString()
        val phone = binding.textFieldphone.text.toString()
        val password = binding.textFieldpassword.text.toString()
        val confirmpassword = binding.textFieldConfirmpassword.text.toString()


        if (isValidForm(name,lastname,email,phone,password,confirmpassword)){
                authProvider.register(email,password).addOnCompleteListener { 
                    if (it.isSuccessful){
                        val client = Client(
                            id = authProvider.getId(),
                            name = name,
                            lastName = lastname,
                            email = email,
                            phone = phone
                        )

                        clientProvider.create(client).addOnCompleteListener {
                            if(it.isSuccessful){
                                Toast.makeText(this@RegisterActivity, "Registro Exitoso", Toast.LENGTH_SHORT).show()
                                goToMap()
                            }
                            else{
                                Toast.makeText(this@RegisterActivity, "Error almacenando datos usuario"+it.exception.toString(), Toast.LENGTH_SHORT).show()
                                Log.d("FIREBASE","Error: " + it.exception.toString())
                            }
                        }
                    }
                    else{
                        Toast.makeText(this@RegisterActivity, "Registro Fallido" + it.exception.toString(), Toast.LENGTH_LONG).show()
                        Log.d("FIREBASE","Error: " + it.exception.toString())
                    }
                }
        }
    }


    private fun goToMap(){
        val i = Intent(this, MapActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)
    }

    private fun isValidForm(name:String,lastname:String,email:String,phone:String, password:String,confirmPassword:String):Boolean{
        if(name.isEmpty()){
            Toast.makeText(this, "Debes ingresar nombre", Toast.LENGTH_SHORT).show()
            return false
        }
        if(lastname.isEmpty()){
            Toast.makeText(this, "Debes ingresar Apellido", Toast.LENGTH_SHORT).show()
            return false
        }
        if(email.isEmpty()){
            Toast.makeText(this, "Debes ingresar Email", Toast.LENGTH_SHORT).show()
            return false
        }
        if(phone.isEmpty()){
            Toast.makeText(this, "Debes ingresar Telefono", Toast.LENGTH_SHORT).show()
            return false
        }
        if(password.isEmpty()){
            Toast.makeText(this, "Debes ingresar Contraseña", Toast.LENGTH_SHORT).show()
            return false
        }
        if(password.isEmpty()){
            Toast.makeText(this, "Debes ingresar Confirmacion de Contraseña", Toast.LENGTH_SHORT).show()
            return false
        }
        if(password != confirmPassword){
            Toast.makeText(this, "Las contraseñas deben coincidir", Toast.LENGTH_SHORT).show()
            return false
        }

        if(password.length<6){
            Toast.makeText(this, "Las contraseñas deben tener 6 caracteres", Toast.LENGTH_LONG).show()
            return false
        }

        return true

    }


    private fun goToLogin() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}