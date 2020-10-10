package com.angeloplacebo.minhastarefas

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AlertDialogLayout
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var lvTarefas: ListView
    private lateinit var dao: TarefaDAO
    private lateinit var lista: ArrayList<Tarefa>
    var CADASTRO = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fabAdd = findViewById(R.id.fabAdd)
        lvTarefas = findViewById(R.id.lvTarefas)

        dao = TarefaDAO(this)
        lista = dao.get()

        lvTarefas.adapter = TarefaAdapter(this, lista)

        fabAdd.setOnClickListener {
            startActivityForResult(Intent(this, FormActivity::class.java), CADASTRO)
        }

        lvTarefas.onItemLongClickListener = clickLongo()
        lvTarefas.onItemClickListener = clickCurto()

    }


    fun editar(position: Int) {

        val tarefa = lista.get(position)

        Log.i("APP_TAREFAS", tarefa.toString())

        val dialog = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.activity_form, null)

        val tvTitle = dialogView.findViewById<TextView>(R.id.tvFormTitle)
        val etNome = dialogView.findViewById<EditText>(R.id.etFormNome)
        val llbuttons = dialogView.findViewById<LinearLayout>(R.id.llButtons)
        val sStatus = dialogView.findViewById<Spinner>(R.id.sStatus)
        val np = dialogView.findViewById<NumberPicker>(R.id.npFormPrioridade)
        np.maxValue = 10
        np.minValue = 1

        etNome.setText(tarefa.nome)
        np.value = tarefa.prioridade
        when (tarefa.status) {
            "Aberto" -> sStatus.setSelection(0)
            "Executando" -> sStatus.setSelection(1)
            "Concluído" -> sStatus.setSelection(2)
        }

        (llbuttons.parent as ViewGroup).removeView(llbuttons)
        tvTitle.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRed))
        tvTitle.text = "Editar Tarefa"

        dialog.setNegativeButton("Cancelar") { _, _ -> }
        dialog.setPositiveButton("Salvar",
            DialogInterface.OnClickListener { dialog, id ->
                tarefa.nome = etNome.text.toString()
                tarefa.prioridade = np.value
                tarefa.status = sStatus.selectedItem.toString()
                dao.update(tarefa)
                lvUpdate()

                Toast.makeText(this, "Tarefa Atualizada", Toast.LENGTH_SHORT).show()
            })

        dialog.setView(dialogView)
        dialog.create().show()
    }

    private fun lvUpdate() {
        (lvTarefas.adapter as TarefaAdapter).notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CADASTRO) {
            if (resultCode == RESULT_OK) {
                val tarefa = data?.getSerializableExtra("Tarefa") as Tarefa
                dao.inserir(tarefa)
                lista.add(tarefa)
                lvUpdate()
            }
        }
    }

    inner class clickCurto() : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            editar(position)
        }

    }

    inner class clickLongo() : AdapterView.OnItemLongClickListener {
        override fun onItemLongClick(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ): Boolean {
            val tarefa = lista.get(position)

            val dialog = AlertDialog.Builder(this@MainActivity)
            dialog.setTitle("Deseja Excluir a tarefa ${tarefa.nome} ?")
            dialog.setNegativeButton("Cancelar",null)
            dialog.setPositiveButton("Confirmar") { dialogInterface, which ->
                dao.delete(tarefa.id)
                lista.removeAt(position)
                lvUpdate()
                Toast.makeText(this@MainActivity, "Tarefa Deletada", Toast.LENGTH_SHORT).show()
            }
            dialog.create().show()
            return true
        }

    }
}