package com.angeloplacebo.minhascores

class ListView_Model {
    var name :String
    var code :String

    constructor(name: String,code:String){
        this.name = name
        this.code = code
    }

    fun getColorName():String {
        return this.name.toString()
    }
    fun setColorName(name:String){
        this.name = name
    }

    fun getColorCode():String{
        return this.code
    }
    fun setColorCode(code:String){
        this.code = code
    }
    fun getColor():String{
        return "Nome: ${this.name} - Código: ${this.code}"
    }
}