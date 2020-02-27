package app.recrutamento.android.challengealpha.util

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction


fun AppCompatActivity.navigateToFragment(
    container: Int,
    fragmentToNavigate: androidx.fragment.app.Fragment,
    tag: String,
    bundle: Bundle? = null
) {
    val fragmentTransaction = this.supportFragmentManager.beginTransaction()
    fragmentToNavigate.arguments = bundle
    fragmentTransaction.replace(container, fragmentToNavigate, tag)
    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
    fragmentTransaction.commit()
}