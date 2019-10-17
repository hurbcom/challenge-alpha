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
    var list = [ResultViewData]()
}

struct ResultViewData {
    var destinationName = ""
    var offerName = ""
    var amenities = [String]()
    var oldPrice = ""
    var newPrice = ""
    var image = ""
    var gallery = [String]()
}


// MARK: - VIEW DELEGATE -
protocol HotelAndPackageListViewDelegate: NSObjectProtocol {
    func startLoading()
    func stopLoading()
    func showError(_ error: ErrorType)
    func setViewData(viewData: HotelAndPackageListViewData)
}

// MARK: - PRESENTER CLASS -
class HotelAndPackageListPresenter {
    
    private weak var viewDelegate: HotelAndPackageListViewDelegate?
    private var viewData = HotelAndPackageListViewData()
    private let service: HotelPackageService
    
    init(viewDelegate: HotelAndPackageListViewDelegate, service: HotelPackageService) {
        self.viewDelegate = viewDelegate
        self.service = service
    }
}

//SERVICE
extension HotelAndPackageListPresenter {
    open func getOffers(query: String, filter: TypeFilter, page: Int) {
        self.viewDelegate?.startLoading()
        self.service.getHotelAndPackage(query: query, filter: filter, page: page) { (result) in
            switch result {
            case.success(let listModel):
                guard let results = listModel.results else { return }
                self.viewData.list = results.map({self.parseFromModelToViewData(model: $0)})
                self.viewDelegate?.setViewData(viewData: self.viewData)
                self.viewDelegate?.stopLoading()
            case .failure(let error):
                self.viewDelegate?.showError(error)
                self.viewDelegate?.stopLoading()
            }
        }
    }
}

//AUX METHODS
extension HotelAndPackageListPresenter {
    private func parseFromModelToViewData(model: ResultsModel) -> ResultViewData {
        var viewData = ResultViewData()
        viewData.destinationName = "\(model.address?.state ?? "") - \(model.address?.country ?? "")"
        viewData.offerName = model.name ?? ""
        model.amenities?.forEach({viewData.amenities.append($0.name ?? "")})
        viewData.oldPrice = String(model.price?.oldPrice ?? 0.0)
        viewData.newPrice = String(model.price?.amountPerDay ?? 0.0)
        viewData.image = model.image ?? model.gallery?.first?.url ?? ""
        model.gallery?.forEach({viewData.gallery.append($0.url ?? "")})
        return viewData
    }
}
