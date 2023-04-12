package br.com.vaniala.starwars.ui.films.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import br.com.vaniala.starwars.databinding.ListItemFilmsBinding
import br.com.vaniala.starwars.domain.model.Films
/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 12/04/23.
 *
 */
class FilmsAdapter(
    var onItemClickListener: (film: Films) -> Unit = {},
) : PagingDataAdapter<Films, FilmsViewHolder>(FilmsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val binding = ListItemFilmsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return FilmsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        val film = getItem(position)
        if (film != null) {
            holder.bind(film, onItemClickListener)
        }
    }
}
