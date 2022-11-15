//
//  PackageServiceSuccessStub.swift
//  Challenge_iOSTests
//
//  Created by Helio Junior on 15/11/22.
//

import Foundation
@testable import Challenge_iOS

struct PackageServiceSuccessStub: PackageServiceProtocol {
    
    func getSuggestionsFrom(text: String, completion: @escaping ([SuggestionModel]) -> Void) {
        let suggestions = [
            SuggestionModel(text: "Gramado, Mato Grosso"),
            SuggestionModel(text: "Gramado, Rio Grande do Sul"),
            SuggestionModel(text: "Pacote Gramado - 2023")
        ]
        completion(suggestions)
    }
    
    func findPackageFrom(query: String, pagination: Int, completion: @escaping (Result<[SearchResultModel], CustomError>) -> Void) {
        let result = SearchResultModel(id: nil,
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
                                       stars: nil)
        completion(.success([result]))
    }
}
