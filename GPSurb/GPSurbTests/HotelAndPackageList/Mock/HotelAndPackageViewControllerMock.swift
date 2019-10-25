//
//  HotelAndPackageViewControllerMock.swift
//  GPSurbTests
//
//  Created by Gilson Santos on 25/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import Foundation

class HotelAndPackageViewControllerMock: NSObject, HotelAndPackageListViewDelegate {
    
    var resultViewData: [ResultViewData]?
    var hotelViewData: HotelAndPackageListViewData?
    var state = State.none
    
    func startLoading() {
        self.state = .loading
    }
    
    func stopLoading() {
    }
    
    func showError(_ error: ErrorType) {
        self.state = .error
    }
    
    func setViewData(viewData: HotelAndPackageListViewData) {
        self.hotelViewData = viewData
        self.state = .success
    }
    
    func setViewDataOfNextPage(viewData: [ResultViewData]) {
        self.resultViewData = viewData
        self.state = .success
    }
}
