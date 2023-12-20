package com.example.task_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Switch
import android.widget.Toast
import com.example.task_app.R
import com.example.task_app.R.*
import com.example.task_app.data.OnTaskCheckedChangeListener
import com.example.task_app.data.Task
import com.example.task_app.data.User

class ListTaskActivity : AppCompatActivity(), OnTaskCheckedChangeListener {
    private lateinit var taskAdapter: MostrarTareas
    private lateinit var listaTareas: MutableList<Task>
    private var usuario: User? = null
    private lateinit var switchSelector: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_task)

        val extras: Bundle? = intent.extras
        usuario = extras?.getSerializable("usuario") as? User

        if (usuario != null) {
            switchSelector = findViewById(R.id.switchSelector)

            val tasks: List<Task> = usuario?.tareas ?: emptyList()
            listaTareas = tasks.toMutableList()

            val listView: ListView = findViewById(R.id.listViewTasks)
            taskAdapter = MostrarTareas(this, listaTareas, this)
            listView.adapter = taskAdapter

            switchSelector.setOnCheckedChangeListener { _, isChecked ->
                actualizarListaTareas(isChecked)
            }
        } else {
            Toast.makeText(this, "No se encontró información del usuario", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onTaskCheckedChanged(task: Task, isChecked: Boolean) {
        // Actualizar el estado de la tarea en la lista del usuario
        usuario?.actualizarEstadoTarea(task, isChecked)

        // Actualizar la lista de tareas que se muestra
        actualizarListaTareas(switchSelector.isChecked)
    }

    private fun actualizarListaTareas(isChecked: Boolean) {
        // Verificar que usuario no sea nulo antes de usarlo
        usuario?.let {
            listaTareas.clear()

            if (isChecked) {
                // Filtrar tareas realizadas
                listaTareas.addAll(obtenerListaTareasRealizadas(it.tareas))
            } else {
                // Filtrar tareas pendientes
                listaTareas.addAll(obtenerListaTareasPendientes(it.tareas))
            }

            taskAdapter.notifyDataSetChanged()
        }
    }

    private fun obtenerListaTareasRealizadas(tasks: List<Task>): List<Task> {
        return tasks.filter { it.check }
    }

    private fun obtenerListaTareasPendientes(tasks: List<Task>): List<Task> {
        return tasks.filterNot { it.check }
    }
}
