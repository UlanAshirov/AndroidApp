package com.uli.androidapp.ui.fragment.character.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.uli.androidapp.databinding.CharacterItemBinding
import com.uli.androidapp.domain.network.model.CharacterResult
import com.uli.androidapp.ui.utils.loadImage

class CharacterAdapter(private val listener: SendIdCharacterListener) :
    PagingDataAdapter<CharacterResult, CharacterAdapter.CharacterViewHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val model = getItem(position)
        holder.onBind(model)
        holder.itemView.setOnClickListener {
            model?.id?.let { it1 -> listener.sendId(it1) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CharacterViewHolder(
            CharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    class CharacterViewHolder(private val binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(characterResult: CharacterResult?) {
            binding.imgItemCharacter.loadImage(binding.imgItemCharacter, characterResult?.image)
            binding.tvItemName.text = characterResult?.name
            binding.tvItemStatus.text = characterResult?.status
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CharacterResult>() {
        override fun areItemsTheSame(oldItem: CharacterResult, newItem: CharacterResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CharacterResult,
            newItem: CharacterResult
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface SendIdCharacterListener {
        fun sendId(id: Int)
    }
}
