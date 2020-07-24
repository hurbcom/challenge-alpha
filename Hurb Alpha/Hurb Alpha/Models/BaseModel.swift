//
//  BaseModel.swift
//  Hurb Alpha
//
//  Created by Thiago Augusto on 24/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import Foundation
import ObjectMapper

class BaseModel: NSObject, Mappable, PropertyNames {
    override init() {}
    
    required init?(map: Map) {
        super.init()
        initialize(map: map)
    }
    
    func mapping(map: Map) {
        initialize(map: map)
    }
    
    private func initialize(map: Map) {
        for property in propertyNames() {
            guard let value: Any = try? map.value(property) else { continue }
            setValue(value, forKey: property)
        }
    }
    
    func toJSON() -> [String : Any] {
        var json = [String : Any]()
        for property in propertyNames() {
            let propertyValue = value(forKey: property)
            json[property] = propertyValue
        }
        return json
    }
}

protocol PropertyNames {
    func propertyNames() -> [String]
}

extension PropertyNames
{
    func propertyNames() -> [String] {
        let mirror = Mirror(reflecting: self)
        let properties = mirror.propertyNames()
        if let parent = mirror.superclassMirror {
            return properties + parent.propertyNames()
        }
        return properties
    }
}
