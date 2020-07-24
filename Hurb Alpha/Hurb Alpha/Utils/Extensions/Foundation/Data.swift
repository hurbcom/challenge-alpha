//
//  Data.swift
//  Hurb Alpha
//
//  Created by Thiago Augusto on 24/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import Foundation

extension Data {
    func modelObject() -> [String : Any]? {
        do {
            let model = try JSONSerialization.jsonObject(with: self, options: []) as? [String : Any]
            
            return model
        } catch {
            Log.shared.show(error: error.localizedDescription)
            return [String : Any]()
        }
    }

    func modelObject(key: String) -> [String : Any]? {
        let model = self.modelObject() ?? [:]
        return model[key] as? [String : Any]
    }
}
