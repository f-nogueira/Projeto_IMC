package com.example.imcapp2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.imcapp2.model.AppDatabase
import kotlinx.coroutines.launch

class HistoricoActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ImcAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)

        recyclerView = findViewById(R.id.recyclerViewHistorico)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ImcAdapter()
        recyclerView.adapter = adapter

        // Inicializa o banco
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "imc_database"
        ).build()

        carregarImcs()
    }

    private fun carregarImcs() {
        lifecycleScope.launch {
            val imcs = db.Dao().getAll()
            adapter.setData(imcs)
        }
    }
}

