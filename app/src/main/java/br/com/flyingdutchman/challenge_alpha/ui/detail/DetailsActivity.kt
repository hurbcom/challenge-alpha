package br.com.flyingdutchman.challenge_alpha.ui.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.os.bundleOf
import br.com.flyingdutchman.challenge_alpha.App
import br.com.flyingdutchman.challenge_alpha.R
import br.com.flyingdutchman.challenge_alpha.commons.color
import br.com.flyingdutchman.challenge_alpha.commons.hide
import br.com.flyingdutchman.challenge_alpha.commons.show
import br.com.flyingdutchman.challenge_alpha.commons.spannable
import br.com.flyingdutchman.challenge_alpha.ui.search.model.SearchResult
import coil.api.load
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.include_toolbar_collapsing_offer_detail.*
import kotlinx.android.synthetic.main.offer_detail_content.*
import kotlinx.android.synthetic.main.result_rails_item.view.*
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
                    }
                }
            } else {
                if (isShow) {
                    isShow = false
                    toolbar.title = ""
                    toolbar_layout.title = ""
                    details_city.show()
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
            details_amenities.text = "Hospedagem com ${searchResult.amenities}"

            val checkDrawable = App.instance.getDrawable(
                R.drawable.ic_check_black_24dp
            )

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

        }
    }


}
