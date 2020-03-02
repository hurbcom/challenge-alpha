package com.barreto.android.presentation.content

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.barreto.android.domain.base.Event
import com.barreto.android.domain.content.model.ContentItem
import com.barreto.android.R
import com.barreto.android.presentation.MainActivity
import com.squareup.picasso.Picasso
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.text.NumberFormat

class ItemActivity : AppCompatActivity() {

    private var contentItem = ContentItem()

    protected val disposable = CompositeDisposable()

    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
    private val titleText by lazy { findViewById<TextView>(R.id.titleText) }
    private val descText by lazy { findViewById<TextView>(R.id.descText) }
    private val priceText by lazy { findViewById<TextView>(R.id.priceText) }
    private val thumbnail by lazy { findViewById<ImageView>(R.id.thumbnail) }
    private val rating by lazy { findViewById<RatingBar>(R.id.rating) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        buildToolbar()

        initialize()
    }

    private fun initialize() {
        titleText.text = contentItem.name
        descText.text = contentItem.description
        contentItem.price?.let {price ->
            priceText.text = NumberFormat.getCurrencyInstance().format(price)
        }

        rating.rating = contentItem.stars.toFloat()

        Picasso.with(this)
            .load(contentItem.image)
            .noFade()
            .placeholder(R.drawable.ic_image_black_24dp)
            .fit()
            .tag("placholder")
            .into(thumbnail)
    }

    private fun buildToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = intent.getStringExtra(MainActivity.TOOLBAR_TITLE) ?: getString(R.string.app_name)
        contentItem = intent.getSerializableExtra(CONTENT_ITEM) as ContentItem
    }

    override fun onDestroy() {

        disposable.clear()
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val CONTENT_ITEM = "contentItem"

        fun buildIntent(
            context: Context,
            contentItem: ContentItem,
            title: String
        ): Intent {

            return Intent(context, ItemActivity::class.java)
                .apply {
                    putExtra(CONTENT_ITEM, contentItem)
                    putExtra(MainActivity.TOOLBAR_TITLE, title)
                }
        }
    }
}