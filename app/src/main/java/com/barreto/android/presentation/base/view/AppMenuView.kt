package com.barreto.android.presentation.base.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.MutableLiveData
import com.barreto.android.R
import com.barreto.android.presentation.base.ui.CircleTransform
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso

class AppMenuView : NavigationView {

    val currentSession = MutableLiveData<String>()
    private var menuItems: List<String> = emptyList()

    private var headerView: View = inflateHeaderView(R.layout.header_navigation)

    constructor(context: Context) : super(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        setNavigationItemSelectedListener {
            (parent as DrawerLayout).closeDrawers()

            currentSession.value =  menuItems[it.itemId]
            true
        }
    }

    fun configUser() {
        val path: String? = null
        Picasso.with(context)
            .load(path)
            .noFade()
            .transform(CircleTransform())
            .placeholder(R.drawable.ic_android_white_24dp)
            .fit()
            .tag("placholder")
            .into(headerView.findViewById<ImageView>(R.id.logoView))

        headerView.findViewById<AppCompatTextView>(R.id.textUsername).text =
            String.format("Olá, %s!", "Usuario")
    }

    fun configMenu(appMenu: List<String>, session: String? = null) {

        if (menu.size() > 0) {
            menu.clear()
        }

        if (appMenu.isNotEmpty()) {
            menuItems = appMenu
            for (i in menuItems.indices) {
                menu.add(0, i, i, menuItems[i]).apply {
                    if (session != null) {
                        isChecked = (session == menuItems[i])
                        isCheckable = isChecked
                    }
                }
            }
        }
    }
}