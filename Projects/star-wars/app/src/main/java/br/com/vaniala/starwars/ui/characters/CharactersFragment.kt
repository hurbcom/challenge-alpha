package br.com.vaniala.starwars.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.vaniala.starwars.R
/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 11/04/23.
 *
 */
class CharactersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }
}
