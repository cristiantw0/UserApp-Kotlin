package com.example.userapp.Activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.userapp.userBox
import com.example.userapp.R
import com.example.userapp.Entities.User
import com.example.userapp.Entities.User_.name
import com.example.userapp.Utils.text
import kotlinx.android.synthetic.main.activity_ver.*
import kotlinx.android.synthetic.main.activity_ver.editTextApellido
import kotlinx.android.synthetic.main.activity_ver.editTextCorreo
import kotlinx.android.synthetic.main.activity_ver.editTextEdad
import kotlinx.android.synthetic.main.activity_ver.editTextNombre


class VerActivity : AppCompatActivity() {

    private lateinit var user: User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver)

        user = intent.getSerializableExtra("user") as User

        llenar()
        buttonActualizar.setOnClickListener { actualizar() }
        buttonDisabled()
    }

    fun llenar() {
        Log.i("ID", "${user}")
        editTextNombre.text = text(user.name)
        editTextApellido.text = text(user.apellido)
        editTextEdad.text = text(user.age.toString())
        editTextCorreo.text = text(user.correo)
    }


    fun actualizar() {

        val name = user.name
        val apellido = user.apellido
        val edad = user.age
        val correo = user.correo

        val banderaNombre = name != editTextNombre.text.toString()

        Log.i("EditText", "${name}")


        if (banderaNombre || apellido != editTextApellido.text.toString() || edad != editTextEdad.text.toString()
                .toInt() || correo != editTextCorreo.text.toString()
        ) {
            val id = user.id
            val nombre = editTextNombre.text.toString()
            val apellido = editTextApellido.text.toString()
            val edad = editTextEdad.text.toString().toInt()
            val correo = editTextCorreo.text.toString()

            val user: User = User(id, nombre, apellido, edad, correo)

            userBox.put(user)
            Toast.makeText(this, "Los datos se han actualizado", Toast.LENGTH_LONG).show()
            goToUsuariosActivity()
        } else {
            Toast.makeText(this, "Los campos son iguales", Toast.LENGTH_LONG).show()
        }
    }

    fun buttonDisabled() {


        val banderaNombre = user.name == editTextNombre.text.toString()
        val banderaApellido = user.apellido == editTextApellido.text.toString()
        val banderaEdad = user.age == editTextEdad.text.toString().toInt()
        val banderaCorreo = user.correo == editTextCorreo.text.toString()

        Log.i("EditText", "${name}")


        if (banderaNombre || banderaApellido || banderaEdad || banderaCorreo) {
            buttonActualizar.isEnabled = false
            aftertext()
        }
    }

    fun aftertext() {
        val name = user.name
        val apellido = user.apellido
        val edad = user.age
        val correo = user.correo
        editTextNombre.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                buttonActualizar.isEnabled = name != editTextNombre.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        editTextApellido.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}


            override fun afterTextChanged(s: Editable?) {

                buttonActualizar.isEnabled = apellido != editTextApellido.text.toString()
            }

        })

        editTextEdad.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}


            override fun afterTextChanged(s: Editable?) {

                buttonActualizar.isEnabled = edad != editTextEdad.text.toString().toInt()
            }
        })

        editTextCorreo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}


            override fun afterTextChanged(s: Editable?) {

                buttonActualizar.isEnabled = correo != editTextCorreo.text.toString()
            }
        })
    }

    private fun goToUsuariosActivity() {
        val intent = Intent(this, UsuariosActivity::class.java)
        //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}