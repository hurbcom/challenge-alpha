package com.barreto.android.presentation.base

import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import com.barreto.android.R
import com.barreto.android.presentation.MainActivity
import com.barreto.android.presentation.base.view.AppMenuView
import com.barreto.android.presentation.content.FavoritesActivity
import timber.log.Timber

abstract class BaseNavigationActivity : AppCompatActivity() {

    private val navigation: AppMenuView by lazy { findViewById<AppMenuView>(R.id.navigation) }
    private val drawerLayout: DrawerLayout by lazy {
        layoutInflater.inflate(
            R.layout.activity_nav,
            null
        ) as DrawerLayout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setContentView(layoutResID: Int) {

        val container = drawerLayout.findViewById<FrameLayout>(R.id.containerLayout)
        layoutInflater.inflate(layoutResID, container, true)

        super.setContentView(drawerLayout)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers()
        } else {
            super.onBackPressed()
        }
    }

    protected fun startMenu(session: String) {

        navigation.currentSession.observe(this, Observer {
            when (it) {
                ITEM_2 -> {
                    startActivity(
                        FavoritesActivity.buildIntent(this, it)
                    )
                }
                else -> {
                    startActivity(
                        MainActivity.buildIntent(this, it)
                    )
                }
            }
        })

        navigation.configUser()
        navigation.configMenu(
            ArrayList<String>().apply {
                this.add(ITEM_1)
                this.add(ITEM_2)
            },
            session
        )
    }

    companion object {
        const val TOOLBAR_TITLE = "toolbarTitle"

        const val ITEM_1 = "Início"
        const val ITEM_2 = "Favoritos"
    }
}