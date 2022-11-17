//
//  HUGScalars.swift
//  
//
//  Created by Theo Mendes on 04/11/21.
//

import Foundation
import Apollo

public typealias HUGScalarSKU = String
public typealias HUGScalarNonNegativeInt = Int
public typealias HUGScalarPositiveInt = Int
public typealias HUGScalarDate = Date
public typealias HUGScalarPosID = String
public typealias HUGScalarLocale = String

extension HUGScalarDate: JSONDecodable, JSONEncodable {
    public init(jsonValue value: JSONValue) throws {
        guard let string = value as? String else {
            throw JSONDecodingError.couldNotConvert(value: value, to: String.self)
        }
        guard let date = ISO8601DateFormatter().date(from: string) else {
            throw JSONDecodingError.couldNotConvert(value: value, to: Date.self)
        }
        self = date
    }
    public var jsonValue: JSONValue {
        let dateFormatter = DateFormatter()
        // Set Date Format
        dateFormatter.dateFormat = "yyyy-MM-dd"
        return dateFormatter.string(from: self)
    }
}
