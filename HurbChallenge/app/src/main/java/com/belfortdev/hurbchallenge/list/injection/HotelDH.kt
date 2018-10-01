package com.belfortdev.hurbchallenge.list.injection

import com.belfortdev.hurbchallenge.core.CoreApplication
import javax.inject.Singleton

@Singleton
object HotelDH {
    private var listComponent: ListComponent? = null

    fun listComponent(): ListComponent {
        if (listComponent == null) {
            listComponent = DaggerListComponent.builder()
                    .appComponent(CoreApplication.appComponent)
                    .build()
        }
        return listComponent as ListComponent
    }

    fun destroyListComponent() {
        listComponent = null
    }

}