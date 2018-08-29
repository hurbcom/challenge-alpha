//
//  Destiny.swift
//  Hotels
//
//  Created by Adolfho Athyla on 29/08/2018.
//  Copyright © 2018 a7hyla. All rights reserved.
//

import UIKit

class Destiny: NSObject {
    var name: String?
    var isSelected = false
    
    var destiniesRef: Destinies?
    
    init(name: String, isSelected: Bool, destiniesRef: Destinies) {
        self.name = name
        self.isSelected = isSelected
        self.destiniesRef = destiniesRef
    }
    
    func select() {
        for destiny in destiniesRef!.destinies {
            destiny.deselect()
        }
        isSelected = true
    }
    
    private func deselect() {
        isSelected = false
    }
}
