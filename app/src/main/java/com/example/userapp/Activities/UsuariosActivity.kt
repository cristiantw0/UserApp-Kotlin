package com.example.userapp.Activities

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userapp.Adapters.UserAdapter
import com.example.userapp.userBox
import com.example.userapp.Listener.UserListener
import com.example.userapp.R
import com.example.userapp.Entities.User
import kotlinx.android.synthetic.main.activity_usuarios.*

class UsuariosActivity : AppCompatActivity() {
    private lateinit var mLayoutManager: RecyclerView.LayoutManager

    private lateinit var adapter: UserAdapter
    private var userList: ArrayList<User> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuarios)

        mLayoutManager = LinearLayoutManager(this)

        loadUsers()
    }

    private fun loadUsers() {
        userList.clear()
        for (user in userBox.all) {
            userList.add(user)
        }
        setRecyclerView()
    }

    private fun setRecyclerView() {
        recycler.setHasFixedSize(true)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.layoutManager = mLayoutManager
        adapter = (UserAdapter(userList, object : UserListener {

            override fun eliminar(user: User) {
                alert(user)
            }

            override fun ver(user: User) {
                goVerActivity(user)
                Toast.makeText(this@UsuariosActivity,"Toy jalando pariente",Toast.LENGTH_SHORT).show()
            }
        }))
        recycler.adapter = adapter
    }


    fun alert(user: User) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¡Alerta!")
        builder.setMessage("¿Estas seguro que deseas eliminar este usuario?")
        builder.setPositiveButton("yes") { dialogInterface: DialogInterface, i: Int ->
            userBox.remove(user.id)
            Toast.makeText(this,"Se ha eliminado el usuario",Toast.LENGTH_SHORT).show()
            loadUsers()
        }

        builder.setNegativeButton(
            "No"
        ) { dialogInterface: DialogInterface, i: Int -> }
        builder.show()
    }

    private fun goVerActivity(user: User) {
        val intent = Intent(this, VerActivity::class.java)
        intent.putExtra("user",user)
        //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}