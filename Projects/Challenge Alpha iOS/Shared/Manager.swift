//
//  Manager.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Theo Mendes on 04/11/21.
//

import Foundation
import HUGraphQL
import Combine

// DISCLAIMER: ESTE MANAGER É SOMENTE PARA REFERÊNCIA, NÃO DEVE SER USADO NO DESAFIO FINAL.

internal final class Manager {
    internal typealias SearchResult = HUGraphQL.SearchQuery.Data.Search?
    internal typealias SearchPackageResult = HUGraphQL.SearchPackageQuery.Data.SearchPackage?
    internal typealias SearchHotelResult = HUGraphQL.SearchHotelQuery.Data.SearchHotel?
    internal typealias SuggestionResult = HUGraphQL.SuggestionsQuery.Data.Suggestion?
    
    private let service = HUGService(enableLog: true)
    internal static let shared = Manager()
    
    private init() {}
    
    internal func performSearch(query: String, pagination: HUGraphQL.SearchInputPagination? = nil) -> AnyPublisher<SearchResult, Error> {
        let subject = PassthroughSubject<SearchResult, Error>()
        
        let query = HUGraphQL.SearchQuery(q: query, pagination: pagination)
        
        service.client.fetch(query: query) { res in
            switch res {
            case .failure(let error):
                subject.send(completion: .failure(error))
            case .success(let graphQLValue):
//                if let errors = graphQLValue.errors {
//                    // HANDLE CUSTOM ERROR
//                    subject.send(completion: .failure(errors))
//                }
                subject.send(graphQLValue.data?.search)
                subject.send(completion: .finished)
            }
        }
        
        return subject.eraseToAnyPublisher()
    }
    
    internal func performPackageSearch(query: String, pagination: HUGraphQL.SearchInputPagination? = nil) -> AnyPublisher<SearchPackageResult, Error> {
        let subject = PassthroughSubject<SearchPackageResult, Error>()
        
        let query = HUGraphQL.SearchPackageQuery(q: "", filters: nil, pagination: pagination, l10n: .init(pos: "br", locale: "pt", currency: "BRL"))
        
        service.client.fetch(query: query) { res in
            switch res {
            case .failure(let error):
                subject.send(completion: .failure(error))
            case .success(let graphQLValue):
//                if let errors = graphQLValue.errors {
//                    // HANDLE CUSTOM ERROR
//                    subject.send(completion: .failure(errors))
//                }
                subject.send(graphQLValue.data?.searchPackage)
                subject.send(completion: .finished)
            }
        }
        
        return subject.eraseToAnyPublisher()
    }
    
    internal func performHotelSearch(query: String, pagination: HUGraphQL.SearchInputPagination? = nil) -> AnyPublisher<SearchHotelResult, Error> {
        let subject = PassthroughSubject<SearchHotelResult, Error>()
        
        let query = HUGraphQL.SearchHotelQuery(
            q: query,
            filters: nil,
            pagination: pagination,
            l10n: .init(pos: "br", locale: "pt", currency: "BRL"),
            checkin: Date(),
            checkout: nil,
            rooms: nil)
        
        service.client.fetch(query: query) { res in
            switch res {
            case .failure(let error):
                subject.send(completion: .failure(error))
                
            case .success(let graphQLValue):
//                if let errors = graphQLValue.errors {
//                    // HANDLE CUSTOM ERROR
//                    subject.send(completion: .failure(errors))
//                }
                subject.send(graphQLValue.data?.searchHotel)
                subject.send(completion: .finished)
            }
        }
        
        return subject.eraseToAnyPublisher()
    }
    
    internal func performSuggestion(query: String, pagination: HUGraphQL.SearchInputPagination? = nil, productType: HUGraphQL.SuggestionProductType? = nil) -> AnyPublisher<SuggestionResult, Error> {
        let subject = PassthroughSubject<SuggestionResult, Error>()
        
        let query = HUGraphQL.SuggestionsQuery(
            q: query,
            limit: 20,
            productType: productType,
            l10n: .init(pos: "br", locale: "pt", currency: "BRL"))
        
        service.client.fetch(query: query) { res in
            switch res {
            case .failure(let error):
                subject.send(completion: .failure(error))
                
            case .success(let graphQLValue):
//                if let errors = graphQLValue.errors {
//                    // HANDLE CUSTOM ERROR
//                    subject.send(completion: .failure(errors))
//                }
                subject.send(graphQLValue.data?.suggestions)
                subject.send(completion: .finished)
            }
        }
        
        return subject.eraseToAnyPublisher()
    }
}
