package br.com.hu.allyson.desafiohu.controller.component

import android.content.Context
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import br.com.hu.allyson.desafiohu.R
import br.com.hu.allyson.desafiohu.controller.interfaces.ComponentFactoryImpl

import br.com.hu.allyson.desafiohu.domain.Hotels

class NoDataComponent: ComponentFactoryImpl {

    override fun buildComponent(rootview: ConstraintLayout, title: Int?, hotels: List<Hotels>?, id: Int?) {

        val context = rootview.context
        var icon = buildCentralIcon(context)
        var title = buildTitle(context)
        var subtitle = buildSubtitle(context)
        rootview.addView(icon)
        rootview.addView(title)
        rootview.addView(subtitle)





        ConstraintSet().apply {
            clone(rootview)
            connect(icon.id, ConstraintSet.START, rootview.id, ConstraintSet.START)
            connect(icon.id, ConstraintSet.TOP, rootview.id, ConstraintSet.TOP, 600)
            connect(icon.id, ConstraintSet.END, rootview.id, ConstraintSet.END)
            connect(icon.id, ConstraintSet.BOTTOM, rootview.id, ConstraintSet.BOTTOM)

            connect(title.id, ConstraintSet.START, rootview.id, ConstraintSet.START)
            connect(title.id, ConstraintSet.TOP, rootview.id, ConstraintSet.TOP)
            connect(title.id, ConstraintSet.END, rootview.id, ConstraintSet.END)
            connect(title.id, ConstraintSet.BOTTOM, icon.id, ConstraintSet.TOP)


            connect(subtitle.id, ConstraintSet.START, rootview.id, ConstraintSet.START)
            connect(subtitle.id, ConstraintSet.TOP, icon.id, ConstraintSet.BOTTOM)
            connect(subtitle.id, ConstraintSet.END, rootview.id, ConstraintSet.END)
            connect(subtitle.id, ConstraintSet.BOTTOM, rootview.id, ConstraintSet.BOTTOM)


            setVerticalBias(title.id, "0.9".toFloat())
            setVerticalBias(subtitle.id, "0.9".toFloat())

            applyTo(rootview)
        }



    }

    fun buildCentralIcon(context: Context):ImageView{
        var icon = ImageView(context)
        icon.setImageResource(R.drawable.ic_hotel)
        var layoutParam = LinearLayout.LayoutParams(120, 120)
        icon.layoutParams = layoutParam
        icon.id = R.id.icon
        return icon
    }

    fun buildTitle(context: Context):TextView{
        var title = TextView(context)
        var layoutParam = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        title.layoutParams = layoutParam
        title.id = R.id.title
        title.text = "Sem dados para exibir"
        return title
    }

    fun buildSubtitle(context: Context):TextView{
        var title = TextView(context)
        var layoutParam = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        title.layoutParams = layoutParam
        title.id = R.id.subtitle
        title.text = "Não foram encontrados hoteis para exibir"
        return title
    }

}