package com.example.task_app.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.example.task_app.R
import com.example.task_app.data.OnTaskCheckedChangeListener
import com.example.task_app.data.Task


class MostrarTareas(
    private val context: Context,
    private val tasks: List<Task>,
    private val onTaskCheckedChangeListener: OnTaskCheckedChangeListener
) : BaseAdapter() {

    override fun getCount(): Int = tasks.size
    override fun getItem(position: Int): Any = tasks[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.task_item, parent, false)

        val taskNameTextView: TextView = view.findViewById(R.id.textViewTaskName)
        val taskDescriptionTextView: TextView = view.findViewById(R.id.textViewTaskDescription)
        val checkBoxTask: CheckBox = view.findViewById(R.id.checkBoxTask)
        val priorityTextView: TextView = view.findViewById(R.id.textViewPriority)

        val task = getItem(position) as Task

        taskNameTextView.text = task.titulo
        taskDescriptionTextView.text = task.descripcion
        priorityTextView.text = task.prioridad.toString()

        // Gestionar el estado del CheckBox
        checkBoxTask.isChecked = task.check

        // Notificar al Activity cuando cambia el estado del CheckBox
        checkBoxTask.setOnCheckedChangeListener { _, isChecked ->
            onTaskCheckedChangeListener.onTaskCheckedChanged(task, isChecked)
        }

        return view
    }
}