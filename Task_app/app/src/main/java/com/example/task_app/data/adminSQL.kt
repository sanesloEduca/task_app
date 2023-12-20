package com.example.task_app.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class adminSQL (context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, name, factory, version){

    val TABLE_USER="usuarios"
    val TABLE_TASK="tareas"

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_DATABASE_USER = (
                "CREATE TABLE $TABLE_USER("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "nombre TEXT,"
                        + "clave TEXT"
                        + ")"
                )

        val CREATE_DATABASE_TASK = (
                "CREATE TABLE $TABLE_TASK("
                    + "id_tarea INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "titulo TEXT,"
                    + "descripcion TEXT,"
                    + "id_usuario REFERENCES usuarios(id)"
                    + ")"
                )

        db?.execSQL(CREATE_DATABASE_USER)
        db?.execSQL(CREATE_DATABASE_TASK)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("drop table if exists $TABLE_USER")
        db?.execSQL("drop table if exists $TABLE_TASK")
        onCreate(db)
    }

    fun isUsuarioRegistrado(nombreUsuario:String, clave:String):Int{
        val db = readableDatabase
        val query = "SELECT id FROM $TABLE_USER WHERE nombre=? AND clave=?"
        val cursor = db.rawQuery(query, arrayOf(nombreUsuario, clave))
        val selectionArgs = arrayOf(nombreUsuario,clave)

        return try{
            db.rawQuery(query,selectionArgs)?.use { cursor ->
                if(cursor.moveToFirst()) cursor.getInt(0) else -1
            } ?: -1
        }finally{
            db.close()
        }
    }

    fun registrarUsuario(nombreUsuario:String, clave:String){
        val db = writableDatabase
        val contenido = ContentValues().apply {
            put("nombre", nombreUsuario)
            put("clave",clave)
        }
        db.insert(TABLE_USER,null, contenido)
        db.close()
    }

    @SuppressLint("Range")
    fun obtenerUsuarioDesdeDB(idUsuario: Int):User?{
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_USER WHERE id=?"
        val cursor = db.rawQuery(query, arrayOf(idUsuario.toString()))

        var usuario:User?=null
        if(cursor.moveToFirst()){
            val nombre = cursor.getString(cursor.getColumnIndex("nombre"))
            val clave = cursor.getString(cursor.getColumnIndex("clave"))
            usuario = User(idUsuario,nombre,clave)
        }

        cursor.close()
        db.close()

        return usuario
    }

    @SuppressLint("Range")
    fun obtenerListaTareasIdUsuario(idUsuario:Int):List<Task>?{
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_TASK WHERE id_usuario=?"
        val selectionArgs = arrayOf(idUsuario.toString())

        val listaTareasUsuario= mutableListOf<Task>()

        db.rawQuery(query,selectionArgs)?.use { cursor ->
            while(cursor.moveToNext()){
                val idTarea = cursor.getInt(cursor.getColumnIndex("id_tarea"))
                val titulo = cursor.getString(cursor.getColumnIndex("titulo"))
                val descripcion = cursor.getString(cursor.getColumnIndex("descripcion"))
                val prioridad = cursor.getString(cursor.getColumnIndex("prioridad"))
                val idUsuario = cursor.getInt(cursor.getColumnIndex("id_usuario"))

                listaTareasUsuario.add(Task(idTarea,titulo,descripcion,prioridad,idUsuario))
            }
        }

        db.close()
        return listaTareasUsuario
    }
}