//
//  HotelMapper.swift
//  HotelSearch
//
//  Created by Tulio Parreiras on 14/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation

final public class HotelMapper {
    
    struct Root: Decodable {
        let hotels: [RemoteHotelItem]
        
        enum CodingKeys: String, CodingKey {
            case hotels = "result"
        }
    }
    
    private init() { }
    
    public static func map(_ data: Data, from response: HTTPURLResponse) throws -> [Hotel] {
        guard response.statusCode == 200, let root = try? JSONDecoder().decode(Root.self, from: data) else {
            throw RemoteHotelSearcher.Error.invalidData
        }
        return root.hotels.map { $0.item }
    }
    
}
