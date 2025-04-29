package com.example.imcapp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imcapp2.model.ImcEntity

class ImcAdapter(
    private val onEditClick: (ImcEntity) -> Unit,
    private val onDeleteClick: (ImcEntity) -> Unit
) : ListAdapter<ImcEntity, ImcAdapter.ImcViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImcViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_imc, parent, false)
        return ImcViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImcViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onEditClick, onDeleteClick)
    }

    class ImcViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtPeso: TextView = itemView.findViewById(R.id.txtPeso)
        private val txtImc: TextView = itemView.findViewById(R.id.txtImc)
        private val btnEdit: ImageButton = itemView.findViewById(R.id.btnEdit)
        private val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)

        fun bind(item: ImcEntity, onEdit: (ImcEntity) -> Unit, onDelete: (ImcEntity) -> Unit) {
            txtPeso.text = "Peso: ${item.peso} kg"
            txtImc.text = "IMC: %.2f".format(item.imc)

            btnEdit.setOnClickListener { onEdit(item) } // Chama onEditClick com o item atual
            btnDelete.setOnClickListener { onDelete(item) }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<ImcEntity>() {
            override fun areItemsTheSame(oldItem: ImcEntity, newItem: ImcEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ImcEntity, newItem: ImcEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}