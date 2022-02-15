package com.isranascimento.utils.extensions

import androidx.fragment.app.Fragment
import com.isranascimento.utils.R

fun Fragment.navigateToScreen(destination: Fragment) {
    activity?.supportFragmentManager
        ?.beginTransaction()
        ?.setCustomAnimations(R.anim.slide_in, R.anim.empty, R.anim.empty, R.anim.slide_out)
        ?.add(
            IdentifierUtils.getItemIdByIdentifier(requireContext(), "root_container"),
            destination
        )
        ?.addToBackStack(null)
        ?.commit()
}