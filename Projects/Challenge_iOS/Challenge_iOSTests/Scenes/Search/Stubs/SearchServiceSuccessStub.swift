//
//  SearchServiceSuccessStub.swift
//  Challenge_iOSTests
//
//  Created by Helio Junior on 14/11/22.
//

import Foundation
@testable import Challenge_iOS

struct SearchServiceSuccessStub: SearchServiceProtocol {
    func getSuggestionsFrom(text: String, completion: @escaping (Result<[SuggestionModel], CustomError>) -> Void) {
        let suggestions = [
            SuggestionModel(text: "Rio de Janeiro"),
            SuggestionModel(text: "Rio Claro"),
            SuggestionModel(text: "Rio Negro"),
            SuggestionModel(text: "Rio Branco")
        ]
        completion(.success(suggestions))
    }
    
    func fetchSearchFrom(query: String, pagination: Int, completion: @escaping (Result<[SearchResultModel], CustomError>) -> Void) {
        let result = [
            SearchResultModel(id: nil,
                              name: "Imperial Plaza Hotel",
                              description: "Localizado a 15 km da cidade de Brasília e 22 km do Aeroporto Internacional JK",
                              smallDescription: "Localizado a 15 km da cidade de Brasília e 22 km do Aeroporto Internacional JK",
                              category: .hotel,
                              isAvailable: true,
                              huFreeCancellation: false,
                              gallery: [
                                SearchResultModel.Gallery(url: "https://thumbcdn-z.hotelurbano.net/b1UA0AzQ5ztR0YyYvKsldRLCUPc=/origxorig/center/middle/filters:quality(70)/https://novo-hu.s3.amazonaws.com/reservas/ota/prod/hotel/519158/2016072814697157749_20180131144941.jpg")
                              ],
                              sku: "HT-VKLT-0-0-0-0-0-0-0-0-0",
                              price: SearchResultModel.Price(amount: 157.90000000000001, currency: "BRL"),
                              address: SearchResultModel.Address(state: "Distrito Federal",
                                                                 country: "Brasil",
                                                                 city: "Brasília",
                                                                 geoLocation: nil),
                              amenities: [],
                              quantityDescriptors: nil,
                              startDate: nil,
                              endDate: nil,
                              stars: nil),
            SearchResultModel(id: "1413827",
                              name: "Pacote de Viagem - CDesign Hotel (Rio de Janeiro) - 2023",
                              description: "Aéreo + Hospedagem com Café da Manhã",
                              smallDescription: "Aéreo + Hospedagem com Café da Manhã",
                              category: .pacote,
                              isAvailable: true,
                              huFreeCancellation: nil,
                              gallery: [
                                SearchResultModel.Gallery(url: "https://thumbcdn-z.hotelurbano.net/J9mbUib9kfYqpkwj4Rhc5LXVXak=/origxorig/center/middle/filters:quality(70)/https://s3.amazonaws.com/legado-prod/prod/ofertas/imagens/2022/10/24/10/38/109925744.jpg")
                              ],
                              sku: "HT-7LSJ-0-0-0-0-0-0-0-0-0",
                              price: SearchResultModel.Price(amount: 129900, currency: "BRL"),
                              address: SearchResultModel.Address(state: "Rio de Janeiro",
                                                                 country: "Brasil",
                                                                 city: "Rio de Janeiro",
                                                                 geoLocation: nil),
                              amenities: [],
                              quantityDescriptors: SearchResultModel.QuantityDescriptors(maxPeople: 2, duration: 1),
                              startDate: nil,
                              endDate: nil,
                              stars: nil)
        ]
        completion(.success(result))
    }
}
