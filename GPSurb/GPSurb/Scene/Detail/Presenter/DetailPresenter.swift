//
//  DetailPresenter.swift
//  GPSurb
//
//  Created by Gilson Santos on 18/10/19.
//  Copyright (c) 2019 Gilson Santos. All rights reserved.
//

import Foundation

// MARK: - PRESENTER CLASS -
class DetailPresenter {
    private let dataBase = FavoritePersistence()
}

//SERVICE
extension DetailPresenter {
    public func addOrRemoveFavorite(viewData: ResultViewData) {
        self.dataBase.createOrRemoveFavoriteDataBase(object: viewData)
    }
    
    public func isExistFavorite(sku: String) -> Bool {
        return self.dataBase.isFavorite(sku: sku)
    }
}
