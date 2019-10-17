//
//  HotelAndPackageListPresenter.swift
//  GPSurb
//
//  Created by Gilson Santos on 17/10/19.
//  Copyright (c) 2019 Gilson Santos. All rights reserved.
//

import Foundation

// MARK: - STRUCT VIEW DATA -
struct HotelAndPackageListViewData {
    
}

// MARK: - VIEW DELEGATE -
protocol HotelAndPackageListViewDelegate: NSObjectProtocol {
    
}

// MARK: - PRESENTER CLASS -
class HotelAndPackageListPresenter {
    
    private weak var viewDelegate: HotelAndPackageListViewDelegate?
    private var viewData = HotelAndPackageListViewData()
    
    init(viewDelegate: HotelAndPackageListViewDelegate) {
        self.viewDelegate = viewDelegate
    }
}

//SERVICE
extension HotelAndPackageListPresenter {
    
}

//AUX METHODS
extension HotelAndPackageListPresenter {
    
}

//DATABASE
extension HotelAndPackageListPresenter {
    
}
