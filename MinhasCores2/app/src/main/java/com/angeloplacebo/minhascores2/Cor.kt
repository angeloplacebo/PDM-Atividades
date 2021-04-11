package com.angeloplacebo.minhascores2

import java.io.Serializable

class Cor(var nome:String, var codigo:String):Serializable{
    override fun toString(): String {
        return this.nome
    }
}