//
//  RemoteHotelSearcher.swift
//  HotelSearch
//
//  Created by Tulio Parreiras on 14/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation

final public class RemoteHotelSearcher {
    
    public typealias Result = Swift.Result<[Hotel], Swift.Error>
    
    private let url: URL
    private let client: HTTPClient
    
    public enum Error: Swift.Error {
        case invalidData
    }
    
    public init(url: URL, client: HTTPClient) {
        self.url = url
        self.client = client
    }
    
    public func searchHotel(with searchText: String, competion: @escaping (RemoteHotelSearcher.Result) -> Void) {
        self.client.get(from: url) { [weak self] result in
            guard self != nil else { return }
            switch result {
            case let .success((data, response)):
                competion(Result {
                    try HotelMapper.map(data, from: response)
                })
            case .failure:
                competion(.failure(RemoteHotelSearcher.Error.invalidData))
            }
        }
    }
    
}
