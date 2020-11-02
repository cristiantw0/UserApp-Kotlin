package com.example.userapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.userapp.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnRegistro = findViewById<Button>(R.id.buttonRegistrar)
        val btnUsuarios = findViewById<Button>(R.id.buttonUsuarios)

        btnRegistro.setOnClickListener { goToRegistroActivity() }
        btnUsuarios.setOnClickListener { goToUsuariosActivity() }
    }

    private fun goToRegistroActivity() = startActivity(Intent(this, RegistroActivity::class.java))
    private fun goToUsuariosActivity() = startActivity(Intent(this, UsuariosActivity::class.java))


}