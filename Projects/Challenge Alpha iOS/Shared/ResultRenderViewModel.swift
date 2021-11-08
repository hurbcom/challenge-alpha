//
//  ResultRenderViewModel.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Theo Mendes on 04/11/21.
//

import Combine
import Foundation
import Apollo

internal final class ResultRenderViewModel: ObservableObject {
    private var subscriptions = Set<AnyCancellable>()
    @Published var resultText: String = ""
    
    internal func performSearch() {
        Manager.shared
            .performSearch(
                query: "Rio de Janeiro",
                pagination: .init(
                    page: 1,
                    limit: 20,
                    sort: .price,
                    sortOrder: .desc))
            .sink { completion in
                if case .failure(let error) = completion {
                    debugPrint(error.localizedDescription)
                }
            } receiveValue: { [weak self] res in
                let jsonData = try? JSONSerialization.data(withJSONObject: res?.jsonObject as Any, options: [])
                
                if let text = prettyJSON(data: jsonData) {
                    self?.resultText = text
                }
            }
            .store(in: &self.subscriptions)

    }
    
    internal func performSearchPackage() {
        Manager.shared
            .performHotelSearch(
                query: "Rio de Janeiro",
                pagination: .init(
                    page: 1,
                    limit: 20,
                    sort: .price,
                    sortOrder: .desc))
            .sink { completion in
                if case .failure(let error) = completion {
                    debugPrint(error.localizedDescription)
                }
            } receiveValue: { [weak self] res in
                let jsonData = try? JSONSerialization.data(withJSONObject: res?.jsonObject as Any, options: [])
                
                if let text = prettyJSON(data: jsonData) {
                    self?.resultText = text
                }
            }
            .store(in: &self.subscriptions)
    }
    
    internal func performSearchHotel() {
        Manager.shared
            .performHotelSearch(
                query: "Rio de Janeiro",
                pagination: .init(
                    page: 1,
                    limit: 20,
                    sort: .price,
                    sortOrder: .desc))
            .sink { completion in
                if case .failure(let error) = completion {
                    debugPrint(error.localizedDescription)
                }
            } receiveValue: { [weak self] res in
                let jsonData = try? JSONSerialization.data(withJSONObject: res?.jsonObject as Any, options: [])
                
                if let text = prettyJSON(data: jsonData) {
                    self?.resultText = text
                }
            }
            .store(in: &self.subscriptions)
    }
}

fileprivate func prettyJSON(data: Data?) -> String? {
    guard let data = data else { return nil }
    guard let object = try? JSONSerialization.jsonObject(with: data, options: []),
          let data = try? JSONSerialization.data(withJSONObject: object, options: [.prettyPrinted]),
          let prettyPrintedString = String(data: data, encoding:.utf8) else { return nil }

    return prettyPrintedString
}
