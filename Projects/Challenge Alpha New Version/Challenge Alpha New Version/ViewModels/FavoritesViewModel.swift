//
//  FavoritesViewModel.swift
//  Challenge Alpha New Version
//
//  Created by Rafael Oliveira on 15/04/23.
//

import Foundation
import CoreData
import UIKit

struct FavoriteViewModel {
    var result: SavedItem
    
    
    init(_ result:SavedItem){
        self.result = result
    }
    
    var name: String {
        result.titulo!
    }
    
    var description: String {
        result.descricao!
    }
    
    var category: String {
        result.categoria!
    }
    
    var urlImage: String {
        return result.urlDaImagem!
    }
    ///
    
    
}

struct FavoritesListViewModel {
    var favorites : [SavedItem] = []
    func numberOfRowsInSection(_ section:Int) -> Int {
            return favorites.count
        }
    func resultAt( index: Int) -> FavoriteViewModel {
            let info = favorites[index]
        return FavoriteViewModel(info)
        }
}
