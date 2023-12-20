package com.example.task_app.data

import java.io.Serializable

data class Task(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val prioridad: String,
    val idUsuario: Int,
    var check: Boolean = false
):Serializable

