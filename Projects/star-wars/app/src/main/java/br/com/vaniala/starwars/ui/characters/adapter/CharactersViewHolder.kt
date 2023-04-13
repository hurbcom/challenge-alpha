package br.com.vaniala.starwars.ui.characters.adapter

import androidx.recyclerview.widget.RecyclerView
import br.com.vaniala.starwars.BuildConfig
import br.com.vaniala.starwars.databinding.ListItemCharactersBinding
import br.com.vaniala.starwars.domain.model.People
import coil.load

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 13/04/23.
 *
 */
class CharactersViewHolder(
    private val binding: ListItemCharactersBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: People, onItemClickListener: (character: People) -> Unit) {
        binding.root.setOnClickListener {
            onItemClickListener(character)
        }
        binding.itemCharactersName.text = character.name

        val urlImage = getUrlImage(character.url)
        val urlHomeworld = getUrlImage(character.homeworld, "planets")
        character.imagePeople = urlImage
        character.imageHomeworld = urlHomeworld
        binding.itemCharactersImage.load(urlImage)
    }

    private fun getUrlImage(url: String?, type: String = "characters"): String? {
        if (url == null || url.length < 2) return null
        val splits = url.split("/")
        val id = splits[splits.size - 2]
        return "${BuildConfig.BASE_URL_IMAGES}$type/$id.jpg"
    }
}
