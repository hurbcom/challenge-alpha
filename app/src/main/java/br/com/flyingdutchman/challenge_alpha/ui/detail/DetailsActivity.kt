package br.com.flyingdutchman.challenge_alpha.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import br.com.flyingdutchman.challenge_alpha.App
import br.com.flyingdutchman.challenge_alpha.R
import br.com.flyingdutchman.challenge_alpha.commons.color
import br.com.flyingdutchman.challenge_alpha.commons.hide
import br.com.flyingdutchman.challenge_alpha.commons.show
import br.com.flyingdutchman.challenge_alpha.commons.spannable
import br.com.flyingdutchman.challenge_alpha.ui.search.model.SearchResult
import coil.api.load
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.include_toolbar_collapsing_offer_detail.*
import kotlinx.android.synthetic.main.offer_detail_content.*
import kotlin.math.abs

class DetailsActivity : AppCompatActivity() {
    var isShow = false
    var searchResult: SearchResult? = null

    companion object {

        const val SEARCH_RESULT = "search_result"

        @JvmStatic
        fun createIntent(context: Context, searchResult: SearchResult): Intent {
            return Intent(context, DetailsActivity::class.java).apply {
                putExtras(bundleOf(SEARCH_RESULT to searchResult))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        searchResult = intent?.extras?.getParcelable<SearchResult>(SEARCH_RESULT)

        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        addAppBarOffsetListener()

        bindOffer(searchResult)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_detail, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }

            R.id.action_favorite -> {

            }

            R.id.action_share -> {

            }

        }

        return super.onOptionsItemSelected(item)
    }

    private fun addAppBarOffsetListener() {
        app_bar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (abs(verticalOffset) >= appBarLayout.totalScrollRange) {
                if (!isShow) {
                    isShow = true

                    searchResult?.let {
                        toolbar.title = it.city
                        toolbar_layout.title = it.city
                        details_city.hide()

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            window.statusBarColor = resources.getColor(R.color.colorPrimary)
                        }

                        ViewCompat.setOnApplyWindowInsetsListener(
                            details_activity_coordinator_layout
                        ) { v, insets ->
                            (app_bar.layoutParams as ViewGroup.MarginLayoutParams).topMargin =
                                insets.systemWindowInsetTop
                            insets.consumeSystemWindowInsets()
                        }
                    }
                }
            } else {
                if (isShow) {
                    isShow = false
                    toolbar.title = ""
                    toolbar_layout.title = ""
                    details_city.show()

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.statusBarColor = resources.getColor(android.R.color.transparent)
                    }
                }
            }
        })
    }

    private fun bindOffer(searchResult: SearchResult?) {
        searchResult?.let {

            backdrop.load(searchResult.gallery[0].url) {
                placeholder(
                    android.R.color.darker_gray
                )
            }
            details_city.text = searchResult.city
            details_name.text = searchResult.name

            val checkDrawable = App.instance.getDrawable(
                R.drawable.ic_check_black_24dp
            )

            if (searchResult.isHotel) {
                details_contents_label.text =
                    resources.getString(R.string.details_hotel_contents_label)

                details_amenities.hide()
                details_rating.show()
                details_description.show()

                details_description.text = searchResult.description

            } else {
                details_contents_label.text =
                    resources.getString(R.string.details_package_contents_label)
                details_amenities.text = "Hospedagem com ${searchResult.amenities}"

                details_rating.hide()
                details_description.hide()
                details_amenities.show()
            }

            details_free_cancellation.text =
                spannable {
                    color(
                        resources.getColor(R.color.hurb_green),
                        resources.getString(R.string.free_cancellation)
                    )
                }

            details_free_cancellation.setCompoundDrawablesRelativeWithIntrinsicBounds(
                checkDrawable, null, null, null
            )

            details_price.text = spannable {
                color(
                    resources.getColor(R.color.hurb_orange),
                    searchResult.currentPrice
                )
            }
        } ?: run {
            //Show Custom View Empty State
        }
    }


}
