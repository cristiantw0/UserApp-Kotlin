package com.example.userapp.Entities

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.io.Serializable

//ENTIDAD(MOLDE PARA HACER OBJETOS)
@Entity //el object box lo reconoce como entidad
 data class User (
    @Id(assignable = true) var id: Long,
    var name : String,
    var apellido : String,
    var age : Int,
    var correo : String

):Serializable
