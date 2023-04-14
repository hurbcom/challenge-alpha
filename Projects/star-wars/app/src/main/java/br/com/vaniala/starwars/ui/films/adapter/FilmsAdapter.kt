package br.com.vaniala.starwars.ui.films.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import br.com.vaniala.starwars.databinding.ListItemGridBinding
import br.com.vaniala.starwars.domain.model.Film
/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 12/04/23.
 *
 */
class FilmsAdapter(
    var onItemClickListener: (film: Film) -> Unit = {},
) : PagingDataAdapter<Film, FilmsViewHolder>(FilmsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val binding = ListItemGridBinding.inflate(
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
