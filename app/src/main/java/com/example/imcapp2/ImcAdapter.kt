package com.example.imcapp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.imcapp2.model.ImcEntity

class ImcAdapter : RecyclerView.Adapter<ImcAdapter.ImcViewHolder>() {

    private var imcList = listOf<ImcEntity>()

    // Atualizar os dados
    fun setData(newList: List<ImcEntity>) {
        imcList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImcViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_imc, parent, false)
        return ImcViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImcViewHolder, position: Int) {
        val item = imcList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return imcList.size
    }

    class ImcViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtPeso: TextView = itemView.findViewById(R.id.txtPeso)
        private val txtImc: TextView = itemView.findViewById(R.id.txtImc)

        fun bind(imcEntity: ImcEntity) {
            txtPeso.text = "Peso: ${imcEntity.peso} kg"
            txtImc.text = "IMC: %.2f".format(imcEntity.imc)
        }
    }
}
