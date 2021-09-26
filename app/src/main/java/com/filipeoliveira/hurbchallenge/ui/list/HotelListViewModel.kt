package com.filipeoliveira.hurbchallenge.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filipeoliveira.hurbchallenge.ui.UIState
import com.filipeoliveira.hurbchallenge.ui.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HotelListViewModel() : ViewModel() {

    private val _hotelList = MutableLiveData<UIState<List<HotelUI>>>()
    val hotelList: LiveData<UIState<List<HotelUI>>>
        get() = _hotelList


    fun loadHotelList() {
        _hotelList.value = UIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val response = listOf(
                HotelUI(
                    id = "id",
                    smallDescription = "Uma pequena descrição",
                    amenities = listOf(
                        AmenityUI(
                            name = "Bar",
                            term = AmenityUI.AMENITY_BAR
                        )
                    ),
                    priceCurrency = PriceUI(
                        currency = "BRL",
                        pricePerDay = "150,00"
                    ),
                    huFreeCancellation = true,
                    image = "https://static.hotelurbano.com/reservas/prodaccommodation/30607/58c8470703a3d_pousada-aardvark-inn.jpg",
                    url = "https://www.hurb.com/hoteis/gramado/pousada-aardvark-inn-7987",
                    description = "Espaços charmosos, bons serviços, conforto nos detalhes e ambiente informalmente descontraído. \\r\\nCom atendimento personalizado feito por uma equipe de funcionários e gerenciamento familiar, buscamos oferecer constantemente um alto padrão de hospitalidade aliados a preços acessíveis.",
                    stars = 4,
                    images = listOf(
                        ImageUI(
                            url = "https://static.hotelurbano.com/reservas/prodaccommodation/30607/58c8470703a3d_pousada-aardvark-inn.jpg",
                            description = "AAAAAAAAAAAAFFFFFFFFFFFFFF"
                        ),
                        ImageUI(
                            url = "https://static.hotelurbano.com/reservas/prodaccommodation/30607/58c8470703a3d_pousada-aardvark-inn.jpg",
                            description = "AAAAAAAAAAAAFFFFFFFFFFFFFF"
                        )
                    ),
                    tags = listOf(
                        "TAG 1", "TAG 2", "TAG 3"
                    ),
                    quantityDescriptors = QuantityDescriptorsUI(
                        maxChildren = 2,
                        maxAdults = 2,
                        maxFreeChildrenAge = 12
                    ),
                    name = "NOME",
                    address = AddressUI(
                        city = "Rio de Janeiro",
                        country = "Brasil",
                        state = "Rio de Janeiro",
                        street = "Rua do Filipe, 1"
                    )
                ),
                HotelUI(
                    id = "id",
                    smallDescription = "Uma pequena descrição",
                    amenities = listOf(
                        AmenityUI(
                            name = "Academia",
                            term = AmenityUI.AMENITY_GYM
                        )
                    ),
                    priceCurrency = PriceUI(
                        currency = "BRL",
                        pricePerDay = "150,00"
                    ),
                    huFreeCancellation = true,
                    image = "https://static.hotelurbano.com/reservas/prodaccommodation/30607/58c8470703a3d_pousada-aardvark-inn.jpg",
                    url = "https://www.hurb.com/hoteis/gramado/pousada-aardvark-inn-7987",
                    description = "Espaços charmosos, bons serviços, conforto nos detalhes e ambiente informalmente descontraído. \\r\\nCom atendimento personalizado feito por uma equipe de funcionários e gerenciamento familiar, buscamos oferecer constantemente um alto padrão de hospitalidade aliados a preços acessíveis.",
                    stars = 4,
                    images = listOf(
                        ImageUI(
                            url = "https://static.hotelurbano.com/reservas/prodaccommodation/30607/58c8470703a3d_pousada-aardvark-inn.jpg",
                            description = "AAAAAAAAAAAAFFFFFFFFFFFFFF"
                        ),
                        ImageUI(
                            url = "https://static.hotelurbano.com/reservas/prodaccommodation/30607/58c8470703a3d_pousada-aardvark-inn.jpg",
                            description = "AAAAAAAAAAAAFFFFFFFFFFFFFF"
                        )
                    ),
                    tags = listOf(
                        "TAG 1", "TAG 2", "TAG 3"
                    ),
                    quantityDescriptors = QuantityDescriptorsUI(
                        maxChildren = 2,
                        maxAdults = 2,
                        maxFreeChildrenAge = 12
                    ),
                    name = "NOME",
                    address = AddressUI(
                        city = "Rio de Janeiro",
                        country = "Brasil",
                        state = "Rio de Janeiro",
                        street = "Rua do Filipe, 1"
                    )
                )
            )

            _hotelList.postValue(UIState.Success(response))
        }
    }
}
