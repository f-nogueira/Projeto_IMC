package com.example.imcapp2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtPeso: EditText = findViewById(R.id.edtPeso)
        val edtAltura: EditText = findViewById(R.id.edtAltura)
        val btnCalcular: Button = findViewById(R.id.btnCalcular)
        val txtResultado: TextView = findViewById(R.id.txtResultado)

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
        }
    }

    // Função para calcular o IMC
    private fun calcularIMC(peso: Float, altura: Float): Float {
        return peso / (altura * altura)
    }
}