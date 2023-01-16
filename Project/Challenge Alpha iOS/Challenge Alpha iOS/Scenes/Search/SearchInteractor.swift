//
//  SearchInteractor.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 15/01/23.
//

import Foundation
import Combine
import HUGraphQL

protocol SearchInteractorInput {
    func getSearchResults(query: String, pagination: HUGraphQL.SearchInputPagination?) -> AnyPublisher<[SearchResult], Error>
}

final class SearchInteractor: SearchInteractorInput {

    let service = HUGService(enableLog: true)

    func getSearchResults(query: String, pagination: HUGraphQL.SearchInputPagination? = nil) -> AnyPublisher<[SearchResult], Error> {

        let subject = PassthroughSubject<[SearchResult], Error>()
        let searchQuery = HUGraphQL.SearchQuery(q: query, pagination: pagination)

        service.client.fetch(query: searchQuery) { result in
            switch result {
            case .success(let graphQLValue):
                if let _ = graphQLValue.errors {
                    // TODO: Handle Error
                }

                guard let jsonObject = graphQLValue.data?.search?.jsonObject,
                      let search = self.handleJSONObject(jsonObject) else {

                    // Send an empty result list to be handled
                    subject.send([SearchResult]())
                    subject.send(completion: .finished)
                    return
                }

                let searchResults = search.results
                subject.send(searchResults)
                subject.send(completion: .finished)

            case .failure(let error):
                subject.send(completion: .failure(error))
            }
        }

        return subject.eraseToAnyPublisher()
    }

    private func handleJSONObject(_ jsonObject: [String: Any]) -> Search? {
        do {
            let jsonData = try JSONSerialization.data(withJSONObject: jsonObject as Any, options: [])
            let search = try JSONDecoder().decode(Search.self, from: jsonData)

            return search
        } catch {
            // TODO: Log error
            return nil
        }
    }
}

