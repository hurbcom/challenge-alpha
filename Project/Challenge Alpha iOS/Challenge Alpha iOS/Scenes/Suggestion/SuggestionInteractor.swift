//
//  SuggestionInteractor.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 19/01/23.
//

import Foundation
import Combine
import HUGraphQL

protocol SuggestionInteractorInput {
    func getSuggestions(query: String, pagination: HUGraphQL.SearchInputPagination?, productType: HUGraphQL.SuggestionProductType?) -> AnyPublisher<[SuggestionResult], Error>
}

final class SuggestionInteractor: SuggestionInteractorInput {
    
    private let service = HUGService(enableLog: true)
    
    func getSuggestions(query: String, pagination: HUGraphQL.SearchInputPagination? = nil, productType: HUGraphQL.SuggestionProductType? = nil) -> AnyPublisher<[SuggestionResult], Error> {
        let subject = PassthroughSubject<[SuggestionResult], Error>()
        
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
                if let _ = graphQLValue.errors {
                     // TODO: Handle error
                }
                
                guard let jsonObject = graphQLValue.data?.suggestions?.jsonObject,
                      let suggestions = self.handleJSONObject(jsonObject: jsonObject) else {
                    // Send an empty Result list to be handled by ViewModel
                    subject.send([SuggestionResult]())
                    subject.send(completion: .finished)
                    return
                }
                
                let suggestionReults = suggestions.results
                subject.send(suggestionReults)
                subject.send(completion: .finished)
            }
        }
        
        return subject.eraseToAnyPublisher()
    }
    
    private func handleJSONObject(jsonObject: [String: Any]) -> Suggestions? {
        do {
            let jsonData = try JSONSerialization.data(withJSONObject: jsonObject as Any, options: [])
            let suggestions = try JSONDecoder().decode(Suggestions.self, from: jsonData)
            
            return suggestions
        } catch {
            // TODO: Log error
            return nil
        }
    }
}
