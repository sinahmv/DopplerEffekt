package de.sinah.dopplereffekt

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import de.sinah.dopplereffekt.database.Doppler
import de.sinah.dopplereffekt.databinding.DopplerItemViewBinding

class DopplerAdapter(
    private val listener: (Doppler) -> Unit
) : ListAdapter<Doppler, DopplerAdapter.ViewHolder>(DopplerDiffCallback()) {
    class ViewHolder constructor(val binding: DopplerItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Doppler) {
            binding.dopplerEntity = item
        }
    }

    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = DopplerItemViewBinding.inflate(layoutInflater, parent, false)

            return ViewHolder(binding)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

        holder.itemView.findViewById<Button>(R.id.deleteOne).setOnClickListener{listener(item)}
    }
}

class DopplerDiffCallback : DiffUtil.ItemCallback<Doppler>() {
    override fun areItemsTheSame(oldItem: Doppler, newItem: Doppler): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Doppler, newItem: Doppler): Boolean {
        return oldItem == newItem
    }
}