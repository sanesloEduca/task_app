package com.example.task_app.data

interface OnTaskCheckedChangeListener {
    fun onTaskCheckedChanged(task: Task, isChecked: Boolean)
}