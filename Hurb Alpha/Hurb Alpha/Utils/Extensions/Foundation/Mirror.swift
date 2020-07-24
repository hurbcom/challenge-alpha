//
//  Mirror.swift
//  Hurb Alpha
//
//  Created by Thiago Augusto on 24/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import Foundation

extension Mirror {
    func propertyNames() -> [String]  {
        return children.compactMap { $0.label }
    }
}
