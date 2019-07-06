//
//  Collection.swift
//  HurbChallenge
//
//  Created by Felipe Alves on 06/07/19.
//  Copyright © 2019 Bolzaniapps. All rights reserved.
//

import Foundation

/*
 Essa extensão permite acessar indices potencialmente inexistentes em um array e retornar um optional, sem ocasionar uma exceção
 */
extension Collection {
    
    subscript (safe index: Index) -> Element? {
        return indices.contains(index) ? self[index] : nil
    }
}
