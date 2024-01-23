package com.vdemelo.starwarswiki.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vdemelo.starwarswiki.R
import com.vdemelo.starwarswiki.databinding.ActivityDetailsBinding
import com.vdemelo.starwarswiki.utils.setTextOrHideView
import com.vdemelo.starwarswiki.utils.setImageOrHideView

private const val TITLE_EXTRA = "title"
private const val IMAGE_EXTRA = "image"
private const val FIELD_ONE_EXTRA = "field_one"
private const val FIELD_TWO_EXTRA = "field_two"
private const val FIELD_THREE_EXTRA = "field_three"

class DetailsActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewBinding()
        setData(intent)
    }

    private fun setViewBinding() {
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setData(intent: Intent) {
        val title: String? = intent.getStringExtra(TITLE_EXTRA)
        val imageUrl: String? = intent.getStringExtra(IMAGE_EXTRA)
        val fieldOne: String? = intent.getStringExtra(FIELD_ONE_EXTRA)
        val fieldTwo: String? = intent.getStringExtra(FIELD_TWO_EXTRA)
        val fieldThree: String? = intent.getStringExtra(FIELD_THREE_EXTRA)

        binding.title.text = title ?: getString(R.string.details_screen_title_default)
        binding.imageView.setImageOrHideView(imageUrl)
        binding.fieldOne.setTextOrHideView(fieldOne)
        binding.fieldTwo.setTextOrHideView(fieldTwo)
        binding.fieldThree.setTextOrHideView(fieldThree)
    }

    companion object {
        fun getIntent(
            context: Context,
            title: String,
            imageUrl: String? = null,
            fieldOne: String? = null,
            fieldTwo: String? = null,
            fieldThree: String? = null
        ): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(TITLE_EXTRA, title)
            intent.putExtra(IMAGE_EXTRA, imageUrl)
            intent.putExtra(FIELD_ONE_EXTRA, fieldOne)
            intent.putExtra(FIELD_TWO_EXTRA, fieldTwo)
            intent.putExtra(FIELD_THREE_EXTRA, fieldThree)
            return intent
        }
    }

}
