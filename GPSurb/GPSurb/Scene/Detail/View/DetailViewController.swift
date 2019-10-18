//
//  DetailViewController.swift
//  GPSurb
//
//  Created by Gilson Santos on 18/10/19.
//  Copyright (c) 2019 Gilson Santos. All rights reserved.
//

import UIKit

class DetailViewController: UIViewController {
    
    // MARK: OUTLETS
    
    // MARK: CONSTANTS
    
    // MARK: VARIABLES
    private var presenter: DetailPresenter!
    private lazy var viewData:DetailViewData = DetailViewData()
    
    // MARK: IBACTIONS
}

//MARK: - LIFE CYCLE -
extension DetailViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        self.presenter = DetailPresenter(viewDelegate: self)
    }
}

//MARK: - DELEGATE PRESENTER -
extension DetailViewController: DetailViewDelegate {

}

//MARK: - AUX METHODS -
extension DetailViewController {

}
