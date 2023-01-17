//
//  PackageListInteractor.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 16/01/23.
//

import Foundation
import Combine
import HUGraphQL

protocol PackageListInteractorProtocol {
    func getPackages(query: String, pagination: HUGraphQL.SearchInputPagination?) -> AnyPublisher<[PackageResult], Error>
}

internal final class PackageListInteractor: PackageListInteractorProtocol {
    
    private let service = HUGService(enableLog: true)
    
    func getPackages(query: String, pagination: HUGraphQL.SearchInputPagination? = nil) -> AnyPublisher<[PackageResult], Error> {
        
        let subject = PassthroughSubject<[PackageResult], Error>()
        let l10n = HUGraphQL.InputL10n(pos: "br", locale: "pt", currency: "BRL")
        let query = HUGraphQL.SearchPackageQuery(q: query, filters: nil, pagination: pagination, l10n: l10n)
        
        service.client.fetch(query: query) { result in
            switch result {
            case .success(let graphQLValue):
                if let _ = graphQLValue.errors {
                     // TODO: Handle error
                }
                
                guard let jsonObject = graphQLValue.data?.searchPackage?.jsonObject,
                      let searchPackage = self.handleJSONObject(jsonObject: jsonObject) else {
                    // Send an empty Result list to be handled by ViewModel
                    subject.send([PackageResult]())
                    subject.send(completion: .finished)
                    return
                }
                
                let packages = searchPackage.results
                subject.send(packages)
                subject.send(completion: .finished)
                
            case .failure(let error):
                subject.send(completion: .failure(error))
            }
        }
        
        return subject.eraseToAnyPublisher()
    }
    
    private func handleJSONObject(jsonObject: [String: Any]) -> SearchPackage? {
        do {
            let jsonData = try JSONSerialization.data(withJSONObject: jsonObject as Any, options: [])
            let searchPackage = try JSONDecoder().decode(SearchPackage.self, from: jsonData)
            
            return searchPackage
        } catch {
            // TODO: Log error
            return nil
        }
    }
}
