//
//  FavoritesViewController.swift
//  GPSurb
//
//  Created by Gilson Santos on 14/10/19.
//  Copyright (c) 2019 Gilson Santos. All rights reserved.
//

import UIKit

class FavoritesViewController: UIViewController {
    
    // MARK: OUTLETS
    
    // MARK: CONSTANTS
    
    // MARK: VARIABLES
    private var presenter: FavoritesPresenter!
    private lazy var viewData:FavoritesViewData = FavoritesViewData()
    
    // MARK: IBACTIONS
}

//MARK: - LIFE CYCLE -
extension FavoritesViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        self.presenter = FavoritesPresenter(viewDelegate: self)
    }
}

//MARK: - DELEGATE PRESENTER -
extension FavoritesViewController: FavoritesViewDelegate {

}

//MARK: - AUX METHODS -
extension FavoritesViewController {

}
