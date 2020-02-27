package app.recrutamento.android.challengealpha.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import app.recrutamento.android.challengealpha.R
import app.recrutamento.android.challengealpha.model.CategoryAmenitySubtitle

class SubtitleAdapter(val categoryAmenitySubtitles: List<CategoryAmenitySubtitle>, contex) : BaseAdapter(){

    override fun getView(position: Int, view: View?, parent: ViewGroup): View? {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.subtitle_item,parent, false)
        val categoryAmenitySubtitle = categoryAmenitySubtitles.get(position)
        return null
    }

    override fun getItem(item: Int): Any {
        return categoryAmenitySubtitles.get(item)
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return categoryAmenitySubtitles.size
    }

}