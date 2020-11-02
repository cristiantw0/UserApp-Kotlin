package com.example.userapp.Activities

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.userapp.userBox
import com.example.userapp.R
import com.example.userapp.Entities.User
import com.example.userapp.Entities.User_
import kotlinx.android.synthetic.main.activity_registro.*
import kotlinx.android.synthetic.main.activity_registro.editTextApellido
import kotlinx.android.synthetic.main.activity_registro.editTextCorreo
import kotlinx.android.synthetic.main.activity_registro.editTextEdad
import kotlinx.android.synthetic.main.activity_registro.editTextNombre
import kotlinx.android.synthetic.main.activity_ver.*

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        buttonRegistrar.setOnClickListener { registrar() }

        buttonDisabled()
    }

    fun registrar() {

        if (editTextNombre.text.isNotEmpty() && editTextApellido.text.isNotEmpty() && editTextEdad.text.isNotEmpty() && editTextCorreo.text.isNotEmpty()) {
            if (editTextEdad.text.toString().toInt() >= 18) {

                val nombre = editTextNombre.text.toString()
                val apellido = editTextApellido.text.toString()
                val edad = editTextEdad.text.toString().toInt()
                val correo = editTextCorreo.text.toString()

                val user: User = User(0, nombre, apellido, edad, correo)
                userBox.put(user)
                Toast.makeText(this, "Los datos se han guardado", Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(this, "Debes ser mayor de edad", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Los campos estan incompletos", Toast.LENGTH_LONG).show()
        }
        alert()
        cleanEditTexts()
    }

    fun alert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¡Alerta!")
        builder.setMessage("¿Quieres registras otro usuario?")
        builder.setPositiveButton("yes", { dialogInterface: DialogInterface, i: Int -> })

        builder.setNegativeButton(
            "No",
            { dialogInterface: DialogInterface, i: Int -> goToMainActivity() })
        builder.show()
    }

    private fun cleanEditTexts() {
        editTextNombre.text.clear()
        editTextApellido.text.clear()
        editTextCorreo.text.clear()
        editTextEdad.text.clear()
    }

    fun buttonDisabled() {


        val banderaNombre = editTextNombre.text.toString()
        val banderaApellido = editTextApellido.text.toString()
        val banderaEdad = editTextEdad.text.toString()
        val banderaCorreo = editTextCorreo.text.toString()

        Log.i("EditText", "${User_.name}")

        if (banderaNombre.isEmpty() || banderaApellido.isEmpty() || banderaEdad.isEmpty() || banderaCorreo.isEmpty()) {
            buttonRegistrar.isEnabled = false
        }
    }

    private fun goToMainActivity() = startActivity(Intent(this, MainActivity::class.java))
}