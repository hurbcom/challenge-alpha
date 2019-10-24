//
//  DetailPresenter.swift
//  GPSurb
//
//  Created by Gilson Santos on 18/10/19.
//  Copyright (c) 2019 Gilson Santos. All rights reserved.
//

import Foundation

// MARK: - STRUCT VIEW DATA -
struct DetailViewData {
    
}

// MARK: - VIEW DELEGATE -
protocol DetailViewDelegate: NSObjectProtocol {
    
}

// MARK: - PRESENTER CLASS -
class DetailPresenter {
    
    private weak var viewDelegate: DetailViewDelegate?
    private var viewData = DetailViewData()
    private let dataBase = FavoritePersistence()
    
    init(viewDelegate: DetailViewDelegate) {
        self.viewDelegate = viewDelegate
    }
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

//AUX METHODS
extension DetailPresenter {
    
}