//
//  HotelService.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 13/11/22.
//

import Foundation
import HUGraphQL

protocol HotelServiceProtocol {
    func getSuggestionsFrom(text: String,
                            completion: @escaping (Result<[SuggestionModel], CustomError>) -> Void)
    func findHotelFrom(query: String,
                       pagination: Int,
                       completion: @escaping (Result<[SearchResultModel], CustomError>) -> Void)
}

struct HotelService: HotelServiceProtocol {
    
    // MARK: Properties
    private let graphQL = HUGService(enableLog: true)
    
    // MARK: Methods
    func getSuggestionsFrom(text: String,
                            completion: @escaping (Result<[SuggestionModel], CustomError>) -> Void) {
        let query = HUGraphQL.SuggestionsQuery(
            q: text,
            limit: 6,
            productType: .hotel,
            l10n: .init(pos: "br", locale: "pt", currency: "BRL"))
        
        graphQL.client.fetch(query: query) { result in
            switch result {
            case .success(let value):
                if let data = value.data, let dataSuggestions = data.suggestions {
                    let texts = dataSuggestions.results.compactMap({ $0.resultMap["text"] as? String })
                    let suggestions = texts.map({ SuggestionModel(text: $0) })
                    completion(.success(suggestions))
                }
                
            case .failure:
                completion(.failure(.unknown))
            }
        }
    }
    
    func findHotelFrom(query: String,
                       pagination: Int,
                       completion: @escaping (Result<[SearchResultModel], CustomError>) -> Void) {
        
        let pagination = HUGraphQL.SearchInputPagination(page: pagination, limit: 10, sort: nil, sortOrder: nil)
        let query = HUGraphQL.SearchHotelQuery(
            q: query,
            filters: nil,
            pagination: pagination,
            l10n: .init(pos: "br", locale: "pt", currency: "BRL"),
            checkin: Date(),
            checkout: nil,
            rooms: nil)
        
        graphQL.client.fetch(query: query) { result in
            switch result {
            case .success(let value):
                if let search = value.data?.resultMap["searchHotel"] as? [String:Any],
                   let results = search["results"] as? [[String:Any]] {
                    let models = results.compactMap({ JSONDecoder.decode(to: SearchResultModel.self, from: $0) })
                    completion(.success(models))
                } else {
                    completion(.failure(.custom("Não foi encontrado resultados")))
                }
                
            case .failure:
                completion(.failure(.unknown))
            }
        }
    }
}
