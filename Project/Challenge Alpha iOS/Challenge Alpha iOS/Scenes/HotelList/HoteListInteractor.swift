//
//  HoteListInteractor.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 19/01/23.
//

import Foundation
import Combine
import HUGraphQL

protocol HotelListInteractorInput {
    func getHotels(query: String, pagination: HUGraphQL.SearchInputPagination?) -> AnyPublisher<[HotelResult], Error>
}

final class HotelListInteractor: HotelListInteractorInput {
    
    private let service = HUGService(enableLog: true)
    private let logger = LoggerFactory.createLogger(class: HotelListInteractor.self)
    
    func getHotels(query: String, pagination: HUGraphQL.SearchInputPagination? = nil) -> AnyPublisher<[HotelResult], Error> {
        let subject = PassthroughSubject<[HotelResult], Error>()
        
        let query = HUGraphQL.SearchHotelQuery(
            q: query,
            filters: nil,
            pagination: pagination,
            l10n: .init(pos: "br", locale: "pt", currency: "BRL"),
            checkin: Date(),
            checkout: nil,
            rooms: nil)
        
        service.client.fetch(query: query) { result in
            switch result {
            case .success(let graphQLValue):
                if let _ = graphQLValue.errors {
                    // TODO: Handle error
                }
                
                guard let jsonObject = graphQLValue.data?.searchHotel?.jsonObject,
                      let searchHotel = self.handleJSONObject(jsonObject: jsonObject) else {
                    // Send an empty Result list to be handled by ViewModel
                    subject.send([HotelResult]())
                    subject.send(completion: .finished)
                    return
                }
                
                let packages = searchHotel.results
                subject.send(packages)
                subject.send(completion: .finished)
                
            case .failure(let error):
                self.logger.error("Failed to fetch Hotels - error \(error.localizedDescription)")
                subject.send(completion: .failure(error))
            }
        }
        
        return subject.eraseToAnyPublisher()
    }
    
    private func handleJSONObject(jsonObject: [String: Any]) -> SearchHotel? {
        do {
            let jsonData = try JSONSerialization.data(withJSONObject: jsonObject as Any, options: [])
            let searchPackage = try JSONDecoder().decode(SearchHotel.self, from: jsonData)
            
            return searchPackage
        } catch {
            self.logger.error("Failed to decode received Hotels response - error \(error.localizedDescription)")
            return nil
        }
    }
}
