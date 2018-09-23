package br.com.hu.allyson.desafiohu.ui.hotels

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.hu.allyson.desafiohu.R
import br.com.hu.allyson.desafiohu.domain.Hotels
import br.com.hu.allyson.desafiohu.domain.Result
import br.com.hu.allyson.desafiohu.mvp.interfaces.APIHotels
import br.com.hu.allyson.desafiohu.mvp.presenter.PresenterHotels
import br.com.hu.allyson.desafiohu.network.HotelsManager

class HotelsFragment : Fragment(), APIHotels.ViewHotelsImpl {

    private lateinit var presenter: PresenterHotels
    private var hotels = listOf<Hotels>()

    companion object {
        fun newInstance() = HotelsFragment()
    }

    private lateinit var viewModel: HotelsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.hotels_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestHotelsStart()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HotelsViewModel::class.java)
        // TODO: Use the ViewModel
    }


    override fun requestHotelsStart() {
        presenter = PresenterHotels()
        presenter.bind(this, HotelsManager("Rio de Janeiro"))
        presenter.requestHotelsStart()
    }

    override fun requestHotelsSucess(result: Result) {
        hotels = presenter.buildListHotels(result)
    }

    override fun requestHotelsError() {

    }
}
