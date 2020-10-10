package com.angeloplacebo.minhastarefas

import java.io.Serializable

class Tarefa:Serializable{
    var id: Int
    var nome:String
    var prioridade:Int
    var status :String

    constructor(nome:String, prioridade:Int, status:String){
        this.id = -1
        this.nome=nome
        this.prioridade=prioridade
        this.status=status
    }
    constructor(id:Int,nome:String, prioridade:Int, status:String){
        this.id = id
        this.nome=nome
        this.prioridade=prioridade
        this.status=status
    }

    override fun toString(): String {
        return "${id} - ${nome} - ${prioridade} - ${status}"
    }
}