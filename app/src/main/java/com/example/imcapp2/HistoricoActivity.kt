package com.example.imcapp2

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imcapp2.model.ImcEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.room.Room
import com.example.imcapp2.model.AppDatabase

class HistoricoActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ImcAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)

        recyclerView = findViewById(R.id.recyclerViewHistorico)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ImcAdapter(
            onEditClick = { imcEntity -> exibirDialogoEditar(imcEntity) },
            onDeleteClick = { imcEntity -> excluirImc(imcEntity) }
        )
        recyclerView.adapter = adapter
        // Inicializa o banco
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "imc_database"
        )
            .fallbackToDestructiveMigration() // Adicionado fallbackToDestructiveMigration
            .build()

        carregarImcs()
    }

    private fun carregarImcs() {
        lifecycleScope.launch {
            try {
                val imcs = db.Dao().getAll()
                adapter.submitList(imcs)
            } catch (e: Exception) {
                Log.e("HistoricoActivity", "Erro ao carregar IMCs: ${e.localizedMessage}", e)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@HistoricoActivity, "Erro ao carregar histórico: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun exibirDialogoEditar(imcEntity: ImcEntity) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Editar Peso e Altura")

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        val inputPeso = EditText(this)
        inputPeso.hint = "Novo Peso"
        inputPeso.setText(imcEntity.peso.toString())
        layout.addView(inputPeso)

        val inputAltura = EditText(this)
        inputAltura.hint = "Nova Altura"
        inputAltura.setText(imcEntity.altura.toString())
        layout.addView(inputAltura)

        builder.setView(layout)

        builder.setPositiveButton("Salvar") { dialog, _ ->
            val novoPeso = inputPeso.text.toString().toDoubleOrNull()
            val novaAltura = inputAltura.text.toString().toDoubleOrNull()

            if (novoPeso != null && novaAltura != null) {
                editarImc(imcEntity, novoPeso, novaAltura)
            } else {
                Toast.makeText(this, "Entrada inválida, tente novamente!", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }

    private fun editarImc(imcEntity: ImcEntity, novoPeso: Double, novaAltura: Double) {
        val novoImc = calcularImc(novoPeso, novaAltura)

        val novoImcEntity = imcEntity.copy(
            peso = novoPeso.toFloat(),
            altura = novaAltura.toFloat(),
            imc = novoImc.toFloat()
        )
        lifecycleScope.launch {
            db.Dao().update(novoImcEntity)
            carregarImcs()
            withContext(Dispatchers.Main) {
                Toast.makeText(this@HistoricoActivity, "Registro atualizado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun excluirImc(imc: ImcEntity) {
        AlertDialog.Builder(this)
            .setTitle("Excluir Registro")
            .setMessage("Tem certeza que deseja excluir este registro?")
            .setPositiveButton("Sim") { _, _ ->
                lifecycleScope.launch {
                    db.Dao().delete(imc)
                    carregarImcs()
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@HistoricoActivity, "Registro excluído", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    fun calcularImc(peso: Double, altura: Double): Double {
        return if (altura > 0) {
            peso / (altura * altura)
        } else {
            0.0
        }
    }
}