package com.angeloplacebo.minhastarefas

import android.content.ContentValues
import android.content.Context
import java.util.ArrayList

class TarefaDAO {
    var banco: BancoHelper

    constructor(context: Context) {
        this.banco = BancoHelper(context)
    }

    fun inserir(tarefa: Tarefa) {
        val cv = ContentValues()
        cv.put("nome", tarefa.nome)
        cv.put("prioridade", tarefa.prioridade)
        cv.put("status", tarefa.status)
        this.banco.writableDatabase.insert("tarefas", null, cv)
    }

    fun count(): Int {
        val colunas = arrayOf("id")
        val c = this.banco.readableDatabase.query("tarefas", colunas, null, null, null, null, null)
        return c.count
    }

    fun get(): ArrayList<Tarefa> {
        val lista = ArrayList<Tarefa>()
        val colunas = arrayOf("id", "nome", "prioridade", "status")
        val c = this.banco.readableDatabase.query("tarefas", colunas, null, null, null, null, null)
        if (c.count > 0) {
            c.moveToFirst()
            do {
                val id = c.getInt(c.getColumnIndex("id"))
                val nome = c.getString(c.getColumnIndex("nome"))
                val prioridade = c.getInt(c.getColumnIndex("prioridade"))
                val status = c.getString(c.getColumnIndex("status"))
                lista.add(Tarefa(id, nome, prioridade, status))
            } while (c.moveToNext())
        }
        c.close()
        return lista
    }

    fun get(id: Int): Tarefa? {

        val colunas = arrayOf("id", "nome", "prioridade", "status")
        val c = this.banco.readableDatabase.query("tarefas", colunas, null, null, null, null, null)

        if (c.count > 0) {
            c.moveToFirst()
            do {
                if (c.getInt(c.getColumnIndex("id")) == id) {
                    val nome = c.getString(c.getColumnIndex("nome"))
                    val prioridade = c.getInt(c.getColumnIndex("prioridade"))
                    val status = c.getString(c.getColumnIndex("status"))
                    return Tarefa(id, nome, prioridade, status)
                }
            } while (c.moveToNext())
        }
        c.close()
        return null
    }

    fun delete(id: Int) {
        val where = "id = ?"
        val wherep = arrayOf(id.toString())
        this.banco.writableDatabase.delete("tarefas", where, wherep)
    }

    fun update(tarefa: Tarefa) {
        val cv = ContentValues()
        val where = "id = ?"
        val wherep = arrayOf(tarefa.id.toString())
        cv.put("nome", tarefa.nome)
        cv.put("prioridade", tarefa.prioridade)
        cv.put("status", tarefa.status)
        this.banco.writableDatabase.update("tarefas", cv, where, wherep)
    }

    fun get2(id: Int): Tarefa? {
        val tarefa = Tarefa(id, "", -1, "")
        val where = "id = ?"
        val wherep = arrayOf(id.toString())
        val colunas = arrayOf("id", "nome", "prioridade", "status")
        val c =
            this.banco.readableDatabase.query("tarefas", colunas, where, wherep, null, null, null)
        c.moveToFirst()
        try {
            tarefa.nome = c.getString(c.getColumnIndex("nome"))
            tarefa.prioridade = c.getInt(c.getColumnIndex("prioridade"))
            tarefa.status = c.getString(c.getColumnIndex("status"))
        } finally {
            c.close()
        }
        return tarefa
    }

    fun get3(id: Int): Tarefa? {
        val t = Tarefa(id, "", -1, "")
        val db = this.banco.readableDatabase
        val selectQuery = "SELECT * FROM tarefas WHERE ID = $id"
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.count != 0) {
            cursor.moveToFirst();
            if (cursor.count > 0) {
                do {
                    t.nome = cursor.getString(cursor.getColumnIndex("nome"))
                    t.prioridade = cursor.getInt(cursor.getColumnIndex("prioridade"))
                    t.status = cursor.getString(cursor.getColumnIndex("status"))
                } while ((cursor.moveToNext()));
            }
        }
        cursor.close()
        return if (t.prioridade == -1) {
            null
        } else {
            t
        }
    }
}