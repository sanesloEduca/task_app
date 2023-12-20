package com.example.task_app.data

import java.io.Serializable

data class User(
    val id: Int,
    val nombreUsuario: String,
    val clave: String,
    var tareas: MutableList<Task> = mutableListOf()
): Serializable{
    fun actualizarEstadoTarea(task: Task, isChecked: Boolean) {
        val tareaActualizada = tareas.find { it.id == task.id }
        tareaActualizada?.let {
            it.check = isChecked
        }
    }
}


