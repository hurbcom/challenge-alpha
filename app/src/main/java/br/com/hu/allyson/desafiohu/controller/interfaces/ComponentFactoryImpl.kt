package br.com.hu.allyson.desafiohu.controller.interfaces

import androidx.constraintlayout.widget.ConstraintLayout
import br.com.hu.allyson.desafiohu.domain.Hotels

interface ComponentFactoryImpl {

    fun buildComponent(rootview: ConstraintLayout, title: Int? ,hotels: List<Hotels>?, id: Int?)

}