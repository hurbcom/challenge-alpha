package br.com.vaniala.starwars.ui.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import br.com.vaniala.starwars.databinding.ListItemFilmBinding
import br.com.vaniala.starwars.domain.model.Film
import br.com.vaniala.starwars.ui.films.adapter.FilmsDiffCallback

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/04/23.
 *
 */
class FilmsFavoriteAdapter(
    var onItemClickListener: (film: Film) -> Unit = {},
) : ListAdapter<Film, FilmsFavoriteViewHolder>(FilmsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsFavoriteViewHolder {
        val binding = ListItemFilmBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return FilmsFavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmsFavoriteViewHolder, position: Int) {
        val film = getItem(position)
        holder.bind(film, onItemClickListener)
    }

    override fun getItemCount(): Int = currentList.size
}
