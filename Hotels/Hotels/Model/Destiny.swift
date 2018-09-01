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
    
    //referência para o objeto destinies que contém um array de destinos
    var destiniesRef: Destinies?
    
    init(name: String, isSelected: Bool, destiniesRef: Destinies) {
        self.name = name
        self.isSelected = isSelected
        self.destiniesRef = destiniesRef
    }
    
    /*
     > seleciona um destino
    */
    func select() {
        for destiny in destiniesRef!.destinies {
            destiny.deselect()
        }
        isSelected = true
    }
    
    /*
     > deseleciona um destino
     > método privado pois só é usado no escopo desta classe
    */
    private func deselect() {
        isSelected = false
    }
}
