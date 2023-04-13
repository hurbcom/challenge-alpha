package br.com.vaniala.starwars.ui.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import br.com.vaniala.starwars.databinding.ListItemGridBinding
import br.com.vaniala.starwars.domain.model.People

/**w
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 13/04/23.
 *
 */
class CharactersAdapter(
    var onItemClickListener: (characters: People) -> Unit = {},
) : PagingDataAdapter<People, CharactersViewHolder>(CharactersDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val binding = ListItemGridBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return CharactersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        val character = getItem(position)
        if (character != null) {
            holder.bind(character, onItemClickListener)
        }
    }
}
