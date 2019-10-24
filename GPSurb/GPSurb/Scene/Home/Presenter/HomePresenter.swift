//
//  HomePresenter.swift
//  GPSurb
//
//  Created by Gilson Santos on 16/10/19.
//  Copyright (c) 2019 Gilson Santos. All rights reserved.
//

import Foundation
import UIKit

// MARK: - STRUCT VIEW DATA -
struct HomeViewData {
    var bestDestinations = [DestinatonViewData]()
}

struct DestinatonViewData {
    var name = ""
    var image = UIImage()
    var query = ""
}

// MARK: - VIEW DELEGATE -
protocol HomeViewDelegate: NSObjectProtocol {
    func startLoading()
    func stopLoading()
    func showError()
    func setViewData(viewData: HomeViewData)
}

// MARK: - PRESENTER CLASS -
class HomePresenter {
    
    private weak var viewDelegate: HomeViewDelegate?
    private var viewData = HomeViewData()
    private let service: HomeService
    
    init(viewDelegate: HomeViewDelegate, service: HomeService) {
        self.viewDelegate = viewDelegate
        self.service = service
    }
}

//SERVICE
extension HomePresenter {
    func getBestDestinations() {
        self.viewDelegate?.startLoading()
        guard let modelList = self.service.getBestDestination() else {
            self.viewDelegate?.showError()
            self.viewDelegate?.stopLoading()
            return
        }
        self.viewData.bestDestinations = modelList.map({self.parseFromModelToViewData(model: $0)})
        self.viewDelegate?.setViewData(viewData: self.viewData)
        self.viewDelegate?.stopLoading()
    }
    
    private func parseFromModelToViewData(model: BestDestinationModel) -> DestinatonViewData {
        var viewData = DestinatonViewData()
        viewData.name = model.name ?? ""
        viewData.image = UIImage(named: model.imageName ?? "") ?? UIImage()
        viewData.query = model.query ?? ""
        return viewData
    }
}

//AUX METHODS
extension HomePresenter {
    
}

//DATABASE
extension HomePresenter {
    
}
