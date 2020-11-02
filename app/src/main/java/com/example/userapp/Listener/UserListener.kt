package com.example.userapp.Listener

import com.example.userapp.Entities.User

interface UserListener{

    fun eliminar(user: User)
    fun ver(user: User)
}