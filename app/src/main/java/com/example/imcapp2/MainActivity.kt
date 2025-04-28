package com.example.imcapp2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.imcapp2.model.AppDatabase
import com.example.imcapp2.model.ImcEntity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // Instância do banco de dados
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa o banco de dados
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "imc_database"
        ).build()

        val edtPeso: EditText = findViewById(R.id.edtPeso)
        val edtAltura: EditText = findViewById(R.id.edtAltura)
        val btnCalcular: Button = findViewById(R.id.btnCalcular)
        val txtResultado: TextView = findViewById(R.id.txtResultado)
        val btnHistorico: Button = findViewById(R.id.btnHistorico) // Botão novo para ver o histórico

        // Ação ao clicar no botão de calcular
        btnCalcular.setOnClickListener {
            val peso = edtPeso.text.toString().toFloatOrNull()
            val altura = edtAltura.text.toString().toFloatOrNull()

            if (peso == null || altura == null || peso <= 0f || altura <= 0f) {
                Toast.makeText(this, "Por favor, insira valores válidos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val imc = calcularIMC(peso, altura)
            txtResultado.text = "Resultado: IMC = %.2f".format(imc)

            // Chama a função para salvar no banco
            salvarImc(peso, altura, imc)
        }

        // Ação ao clicar no botão de ver histórico
        btnHistorico.setOnClickListener {
            val intent = Intent(this, HistoricoActivity::class.java)
            startActivity(intent)
        }
    }

    // Função para calcular o IMC
    private fun calcularIMC(peso: Float, altura: Float): Float {
        return peso / (altura * altura)
    }

    // Função para salvar o IMC no banco de dados
    private fun salvarImc(peso: Float, altura: Float, imc: Float) {
        lifecycleScope.launch {
            val imcEntity = ImcEntity(
                idade = 0, // Ainda não pega idade, deixamos 0
                peso = peso,
                imc = imc
            )
            db.Dao().insert(imcEntity)
        }
    }
}
