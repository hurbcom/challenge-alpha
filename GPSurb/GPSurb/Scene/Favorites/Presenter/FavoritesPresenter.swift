//
//  FavoritesPresenter.swift
//  GPSurb
//
//  Created by Gilson Santos on 14/10/19.
//  Copyright (c) 2019 Gilson Santos. All rights reserved.
//

import Foundation

//MARK: - STRUCT VIEW DATA -
struct FavoritesViewData {
    
}

//MARK: - VIEW DELEGATE -
protocol FavoritesViewDelegate: NSObjectProtocol {
    
}

//MARK: - PRESENTER CLASS -
class FavoritesPresenter {
    
    private weak var viewDelegate: FavoritesViewDelegate?
    private var viewData = FavoritesViewData()
    
    init(viewDelegate: FavoritesViewDelegate) {
        self.viewDelegate = viewDelegate
    }
}

//SERVICE
extension FavoritesPresenter {
    
}

//AUX METHODS
extension FavoritesPresenter {
    
}

//DATABASE
extension FavoritesPresenter {
    
}
