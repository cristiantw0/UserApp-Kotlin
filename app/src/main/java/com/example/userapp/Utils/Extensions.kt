package com.example.userapp.Utils

import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup

//Metodo para implementar en inflate
fun ViewGroup.inflate(layoutId: Int) = LayoutInflater.from(context).inflate(layoutId, this, false)!!

fun text(text: String): Editable {
    return Editable.Factory.getInstance().newEditable(text)
}