package br.com.vaniala.starwars.ui.films.adapter

import androidx.recyclerview.widget.RecyclerView
import br.com.vaniala.starwars.BuildConfig
import br.com.vaniala.starwars.databinding.ListItemFilmsBinding
import br.com.vaniala.starwars.domain.model.Films
import coil.load

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

        val urlImage = getUrlImage(film.url)
        film.image = urlImage
        binding.itemFilmsImage.load(urlImage)
    }

    fun getUrlImage(url: String?): String? {
        if (url == null || url.length < 2) return null
        val id = url[url.length - 2]
        return "${BuildConfig.BASE_URL_IMAGES}films/$id.jpg"
    }
}
