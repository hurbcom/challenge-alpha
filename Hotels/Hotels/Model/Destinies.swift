//
//  Destinies.swift
//  Hotels
//
//  Created by Adolfho Athyla on 29/08/2018.
//  Copyright © 2018 a7hyla. All rights reserved.
//

import UIKit

class Destinies: NSObject {
    var destinies = [Destiny]()
    
    //destinos escolhidos que podem ser cidades ou até mesmo estados
    func commonDestinies() {
        destinies = [
            Destiny(name: "Fortaleza", isSelected: true, destiniesRef: self),
            Destiny(name: "Rio de Janeiro", isSelected: false, destiniesRef: self),
            Destiny(name: "São Paulo", isSelected: false, destiniesRef: self),
            Destiny(name: "Santa Catarina", isSelected: false, destiniesRef: self),
            Destiny(name: "Pernambuco", isSelected: false, destiniesRef: self),
            Destiny(name: "Rio Grande do Sul", isSelected: false, destiniesRef: self),
            Destiny(name: "Florianópolis", isSelected: false, destiniesRef: self),
            Destiny(name: "Piauí", isSelected: false, destiniesRef: self),
            Destiny(name: "Minas Gerais", isSelected: false, destiniesRef: self),
            Destiny(name: "Espírito Santo", isSelected: false, destiniesRef: self),
            Destiny(name: "Bahia", isSelected: false, destiniesRef: self)
        ]
    }
    
    /*
     > retorna o destino escolhido naquele momento
    */
    func getSelected() -> Destiny {
        let selectedArray = destinies.filter { (destiny) -> Bool in
            return destiny.isSelected
        }
        return selectedArray.first!
    }
    
}
