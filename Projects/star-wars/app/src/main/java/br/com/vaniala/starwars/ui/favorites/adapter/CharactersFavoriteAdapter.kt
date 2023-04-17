package br.com.vaniala.starwars.ui.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import br.com.vaniala.starwars.databinding.ListItemCharacterBinding
import br.com.vaniala.starwars.domain.model.People
import br.com.vaniala.starwars.ui.characters.adapter.CharactersDiffCallback

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/04/23.
 *
 */
class CharactersFavoriteAdapter(
    var onItemClickListener: (character: People) -> Unit = {},
) : ListAdapter<People, CharactersFavoritesViewHolder>(CharactersDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersFavoritesViewHolder {
        val binding = ListItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return CharactersFavoritesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersFavoritesViewHolder, position: Int) {
        val film = getItem(position)
        holder.bind(film, onItemClickListener)
    }

    override fun getItemCount(): Int = currentList.size
}
