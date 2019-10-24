//
//  FavoritesPresenter.swift
//  GPSurb
//
//  Created by Gilson Santos on 14/10/19.
//  Copyright (c) 2019 Gilson Santos. All rights reserved.
//

import Foundation

// MARK: - STRUCT VIEW DATA -
struct FavoritesViewData {
    
}

// MARK: - VIEW DELEGATE -
protocol FavoritesViewDelegate: NSObjectProtocol {
    func showEmpty()
    func setViewData(viewData: [ResultViewData])
}

// MARK: - PRESENTER CLASS -
class FavoritesPresenter {
    
    private weak var viewDelegate: FavoritesViewDelegate?
    private var viewData = FavoritesViewData()
    private let dataBase = FavoritePersistence()
    
    init(viewDelegate: FavoritesViewDelegate) {
        self.viewDelegate = viewDelegate
    }
}

//SERVICE
extension FavoritesPresenter {
    public func getFavoriteInDataBase() {
        guard let viewData = self.dataBase.fetchFavoriteDataBase(), viewData.count > 0 else {
            return
        }
        self.viewDelegate?.setViewData(viewData: viewData)
    }
}

//AUX METHODS
extension FavoritesPresenter {
    
}

//DATABASE
extension FavoritesPresenter {
    
}
