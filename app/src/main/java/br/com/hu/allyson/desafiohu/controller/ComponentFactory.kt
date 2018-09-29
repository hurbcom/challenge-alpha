package br.com.hu.allyson.desafiohu.controller

import br.com.hu.allyson.desafiohu.controller.component.ListHotelsComponent
import br.com.hu.allyson.desafiohu.controller.component.NoDataComponent
import br.com.hu.allyson.desafiohu.controller.interfaces.ComponentFactoryImpl

class ComponentFactory(var type: String) {

    fun getComponent(): ComponentFactoryImpl{

        return when(type){
            "LIST_HOTELS" -> ListHotelsComponent()
            else -> NoDataComponent()
        }



    }

}