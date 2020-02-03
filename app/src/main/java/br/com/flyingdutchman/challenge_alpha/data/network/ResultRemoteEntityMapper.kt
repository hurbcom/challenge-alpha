package br.com.flyingdutchman.challenge_alpha.data.network

import br.com.flyingdutchman.challenge_alpha.data.model.ImageUrl
import br.com.flyingdutchman.challenge_alpha.data.model.Result
import br.com.flyingdutchman.challenge_alpha.data.ResultData
import br.com.flyingdutchman.challenge_alpha.commons.formatForBrazilianCurrency

class ResultRemoteEntityMapper() :
    RemoteEntityMapper<List<Result>, List<ResultData>> {
    override fun mapFromRemote(type: List<Result>): List<ResultData> {
        return type.map {
            ResultData(
                it.id,
                it.name,
                it.url,
                it.description,
                it.smallDescription,
                it.gallery.map { image ->
                    ImageUrl(
                        image.url
                    )
                },
                it.freeCancelation,
                it.price.amount.formatForBrazilianCurrency(),
                it.price.oldPrice.formatForBrazilianCurrency(),
                it.stars,
                it.address.city
            )
        }

    }
}