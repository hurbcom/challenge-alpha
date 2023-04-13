package br.com.vaniala.starwars.ui.characters.adapter

import androidx.recyclerview.widget.DiffUtil
import br.com.vaniala.starwars.domain.model.People

class CharactersDiffCallback : DiffUtil.ItemCallback<People>() {
    override fun areItemsTheSame(oldItem: People, newItem: People): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: People, newItem: People): Boolean =
        oldItem == newItem
}
