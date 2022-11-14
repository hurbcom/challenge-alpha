//
//  SearchService.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 12/11/22.
//

import Foundation
import HUGraphQL

protocol SearchServiceProtocol {
    func getSuggestionsFrom(text: String, completion: @escaping ([SuggestionModel]) -> Void)
    func fetchSearchFrom(query: String, pagination: Int,
                         completion: @escaping (Result<[SearchResultModel], CustomError>) -> Void)
}

struct SearchService: SearchServiceProtocol {
    
    // MARK: Properties
    private let graphQL = HUGService(enableLog: true)
    
    // MARK: Methods
    func getSuggestionsFrom(text: String, completion: @escaping ([SuggestionModel]) -> Void) {
        let query = HUGraphQL.SuggestionsQuery(
            q: text,
            limit: 6,
            productType: nil,
            l10n: .init(pos: "br", locale: "pt", currency: "BRL"))
        
        graphQL.client.fetch(query: query) { result in
            switch result {
            case .success(let value):
                if let data = value.data, let dataSuggestions = data.suggestions {
                    let texts = dataSuggestions.results.compactMap({ $0.resultMap["text"] as? String })
                    let suggestions = texts.map({ SuggestionModel(text: $0) })
                    completion(suggestions)
                }
                
            case .failure(let error):
                print("==> Error: \(error.localizedDescription)")
            }
        }
    }
    
    func fetchSearchFrom(query: String, pagination: Int, completion: @escaping (Result<[SearchResultModel], CustomError>) -> Void) {
        
        let pagination = HUGraphQL.SearchInputPagination(page: pagination, limit: 10, sort: nil, sortOrder: nil)
        let query = HUGraphQL.SearchQuery(q: query, pagination: pagination)
        
        graphQL.client.fetch(query: query) { result in
            switch result {
            case .success(let value):
                if let search = value.data?.resultMap["search"] as? [String:Any],
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
