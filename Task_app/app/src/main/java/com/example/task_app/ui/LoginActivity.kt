package com.example.task_app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.task_app.R
import com.example.task_app.data.Task
import com.example.task_app.data.User
import com.example.task_app.data.adminSQL

class LoginActivity : AppCompatActivity() {
    private val listaUsuarios: MutableList<User> = mutableListOf()
    private val listaTareas: MutableList<Task> = mutableListOf()
    private lateinit var adminSQL: adminSQL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adminSQL = adminSQL(this,"appTask",null,1)

        val btnLogin:Button = findViewById(R.id.btnLogin)
        val etnombre = findViewById<EditText>(R.id.ediTextName)
        val etpsw = findViewById<EditText>(R.id.ediTextPassword)

        btnLogin.setOnClickListener{
            val nombre = etnombre.text.toString()
            val psw = etpsw.text.toString()
            var idUsuario = adminSQL.isUsuarioRegistrado(nombre,psw)

            if(idUsuario!=-1){
                val usuarioAutentificado = adminSQL.obtenerUsuarioDesdeDB(idUsuario)

                if(usuarioAutentificado!=null){
                    usuarioAutentificado.tareas = (adminSQL.obtenerListaTareasIdUsuario(usuarioAutentificado.id) ?: emptyList()).toMutableList()
                    val intent = Intent(this,ListTaskActivity::class.java)
                    intent.putExtra("usuario",usuarioAutentificado)
                    startActivity(intent)
                }
            }else{
                etnombre.text.clear()
                etpsw.text.clear()
                mostrarDialogoRegistro()
            }
        }
    }
    private fun mostrarDialogoRegistro(){
        val builder = AlertDialog.Builder(this)
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.layout_dialogo_registro, null)

        val etnombre = view.findViewById<EditText>(R.id.editTextUsuario)
        val etpsw = view.findViewById<EditText>(R.id.editTextClave)

        builder.setView(view)
        builder.setPositiveButton("Registrar"){ _, _ ->
            val nom = etnombre.text.toString()
            val psw = etpsw.text.toString()
            adminSQL.registrarUsuario(nom,psw)
        }
        builder.setNegativeButton("Cancelar") { _, _ ->

        }
        builder.show()
    }
}