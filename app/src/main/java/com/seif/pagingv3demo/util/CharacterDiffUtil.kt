package com.seif.pagingv3demo.util

import androidx.recyclerview.widget.DiffUtil
import com.seif.pagingv3demo.model.CharacterData

class CharacterDiffUtil : DiffUtil.ItemCallback<CharacterData>() {

    override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.species == newItem.species
    }

}