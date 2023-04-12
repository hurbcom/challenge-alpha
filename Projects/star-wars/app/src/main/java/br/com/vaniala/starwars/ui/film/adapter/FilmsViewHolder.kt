package br.com.vaniala.starwars.ui.film.adapter

import androidx.recyclerview.widget.RecyclerView
import br.com.vaniala.starwars.databinding.ListItemFilmsBinding
import br.com.vaniala.starwars.domain.model.Films
import java.util.*
/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 12/04/23.
 *
 */
class FilmsViewHolder(
    private val binding: ListItemFilmsBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(film: Films, onItemClickListener: (film: Films) -> Unit) {
        binding.root.setOnClickListener {
            onItemClickListener(film)
        }

        binding.itemFilmsName.text = film.title
    }
}
