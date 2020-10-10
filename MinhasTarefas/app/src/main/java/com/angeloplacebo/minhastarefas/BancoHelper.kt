package com.angeloplacebo.minhastarefas

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoHelper(context: Context):SQLiteOpenHelper(context,"tarefas.db",null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "create table if not exists tarefas(" +
                "id integer primary key autoincrement," +
                "nome text," +
                "prioridade integer," +
                "status text)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table tarefas")
        this.onCreate(db)
    }
}