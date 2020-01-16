package com.example.challenge_alpha.ui.resultDetail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.challenge_alpha.R
import com.example.challenge_alpha.databinding.FragmentResultdetailBinding
import com.example.challenge_alpha.di.Injectable
import com.example.challenge_alpha.di.injector
import com.example.challenge_alpha.di.viewModel
import com.ms.square.android.expandabletextview.ExpandableTextView
import java.text.NumberFormat
import java.util.*

/**
 * Fragmento responsável por mostrar com detalhes as informações do hotel/pacote.
 * A classe [displayDetail] tem toda a configuração da view.
 * A toolbar está configurada para lidar com favoritos e ação de compartilhamento.
 */

class ResultDetailFragment : Fragment(), Injectable {


    private val resultDetailViewModel by viewModel(this) {
        injector.resultDetailViewModelFactory.create(args.selectedResult)
    }
    private val args: ResultDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentResultdetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_resultdetail, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = resultDetailViewModel

        setHasOptionsMenu(true)

        return binding.root
    }




    /**
     * Configuração da toolbar.
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.resultsfavorites_menu, menu)

        resultDetailViewModel.isFavorited.observe(this, Observer {
            Log.d("menuTag", "$it")

            if (it) {
                menu[0].setIcon(R.drawable.ic_favorite_select)
                menu[0].isChecked = true
            } else {
                menu[0].setIcon(R.drawable.ic_favorite)
                menu[0].isChecked = false
            }

        })


        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {


        R.id.isFavorited -> {

            if (item.isChecked) {
                resultDetailViewModel.deleteFavorite()
            } else {
                resultDetailViewModel.insertFavorite()
            }

            true
        }
        R.id.share -> {

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    resultDetailViewModel.getResult.value?.resultDetail?.url
                )
                type = "text/plain"
            }
            startActivity(
                sendIntent
            )

            true
        }
        else -> {

            super.onOptionsItemSelected(item)
        }
    }


}