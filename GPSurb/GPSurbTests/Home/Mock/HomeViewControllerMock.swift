//
//  HomeViewControllerMock.swift
//  GPSurbTests
//
//  Created by Gilson Santos on 24/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import Foundation
import GPSurb

class HomeViewControllerMock: NSObject, HomeViewDelegate {
    var viewData: HomeViewData?
    var state = State.none
   
    func startLoading() {
    }
    
    func stopLoading() {
    }
    
    func showError() {
        self.state = .error
    }
    
    func setViewData(viewData: HomeViewData) {
        self.viewData = viewData
        self.state = .success
    }
}
