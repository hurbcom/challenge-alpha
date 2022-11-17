//
//  JSONDecoder+Extensions.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 12/11/22.
//

import Foundation

extension JSONDecoder {
    static func decode<T: Decodable>(to type: T.Type, from dictionary: [String: Any]) -> T? {
        do {
            let jsonData = try JSONSerialization.data(withJSONObject: dictionary, options: [])
            let object = try JSONDecoder().decode(type, from: jsonData)
            return object
        } catch {
            print(error)
            return nil
        }
    }
}
