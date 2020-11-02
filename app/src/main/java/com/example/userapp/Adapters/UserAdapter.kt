package com.example.userapp.Adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.userapp.R
import com.example.userapp.Entities.User
import com.example.userapp.Utils.inflate
import kotlinx.android.synthetic.main.item_usuario.view.*

class UserAdapter(
    private val data: ArrayList<User>,
    private val listener: com.example.userapp.Listener.UserListener
) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var dataCopy: ArrayList<User> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_usuario))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(data[position], listener)

    override fun getItemCount() = data.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User, listener: com.example.userapp.Listener.UserListener) = with(itemView) {

            tvNombre.text = user.name
            tvApellido.text = user.apellido
            tvEdad.text = "${user.age} años"
            tvCorreo.text = user.correo

            clParentContainer.setOnLongClickListener{
                listener.eliminar(user)
                return@setOnLongClickListener true
            }

            clParentContainer.setOnClickListener{listener.ver(user)}

        }
    }
}