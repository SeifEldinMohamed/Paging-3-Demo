package com.seif.pagingv3demo.ui.homeFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.seif.pagingv3demo.databinding.ItemCharacterRowBinding
import com.seif.pagingv3demo.model.CharacterData
import com.seif.pagingv3demo.util.CharacterDiffUtil

class CharactersAdapter :
    PagingDataAdapter<CharacterData, CharactersAdapter.MyViewHolder>(CharacterDiffUtil()) {

    class MyViewHolder(private val binding: ItemCharacterRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CharacterData) {
            binding.tvName.text = item.name
            binding.tvDescription.text = item.species
            Glide.with(binding.root.context).load(item.image)
                .into(binding.ivCharacter)

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemCharacterRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}