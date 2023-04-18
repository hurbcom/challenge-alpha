package br.com.vaniala.starwars.ui.characters.adapter.footer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.vaniala.starwars.databinding.FooterLoaderStateBinding

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 18/04/23.
 *
 */
class CharacterLoadStateAdapter() :
    LoadStateAdapter<CharacterLoadStateAdapter.LoadStateViewHolder>() {

    inner class LoadStateViewHolder(val binding: FooterLoaderStateBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = FooterLoaderStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.binding.apply {
            charactersFooterProgressBar.isVisible = loadState is LoadState.Loading
            charactersFooterMsg.isVisible =
                loadState is LoadState.Error
        }
    }
}
