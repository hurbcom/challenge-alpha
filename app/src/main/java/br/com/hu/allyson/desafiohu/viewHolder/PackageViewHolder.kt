package br.com.hu.allyson.desafiohu.viewHolder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_package.view.*

class PackageViewHolder(context: Context, view: View): RecyclerView.ViewHolder(view) {


    var image_package = view.image_package
    var local_package = view.local_package
    var package_description = view.package_description
    var package_contains = view.package_contains
}