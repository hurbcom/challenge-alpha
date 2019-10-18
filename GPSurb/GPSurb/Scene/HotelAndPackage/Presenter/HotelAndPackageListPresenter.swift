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
    var totalPages = 0
    var currentPage = 1
    var list = [ResultViewData]()
}

struct ResultViewData {
    var destinationName = ""
    var offerName = ""
    var amenities = [String]()
    var oldPrice = ""
    var newPrice = ""
    var urlImageCard = ""
    var gallery = [String]()
    var freeCancellation = false
}


// MARK: - VIEW DELEGATE -
protocol HotelAndPackageListViewDelegate: NSObjectProtocol {
    func startLoading()
    func stopLoading()
    func showError(_ error: ErrorType)
    func setViewData(viewData: HotelAndPackageListViewData)
    func setViewDataOfNextPage(viewData: [ResultViewData])
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
    open func getOffers(query: String, filter: TypeFilter) {
        self.viewDelegate?.startLoading()
        self.service.getHotelAndPackage(query: query, filter: filter, page: self.viewData.currentPage) { (result) in
            switch result {
            case.success(let listModel):
                self.viewData.list.removeAll()
                self.viewData.list = self.parseFromModelToViewData(model: listModel)
                self.viewDelegate?.setViewData(viewData: self.viewData)
                self.viewDelegate?.stopLoading()
            case .failure(let error):
                self.viewDelegate?.showError(error)
                self.viewDelegate?.stopLoading()
            }
        }
    }
    
    open func getOffers(for page: Int, query: String, filter: TypeFilter) {
        self.service.getHotelAndPackage(query: query, filter: filter, page: page) { (result) in
            switch result {
            case.success(let listModel):
                let list = self.parseFromModelToViewData(model: listModel)
                self.viewDelegate?.setViewDataOfNextPage(viewData: list)
            case .failure(let error):
                self.viewDelegate?.showError(error)
            }
        }
    }
}

//AUX METHODS
extension HotelAndPackageListPresenter {
    /// Function for parse Model to ViewData in HURBListModel
    /// - Parameter model: HURBListModel
    private func parseFromModelToViewData(model: HURBListModel) -> [ResultViewData] {
        guard let results = model.results else { return [ResultViewData]()}
        self.viewData.totalPages = model.pagination?.count ?? 0
        return results.map({self.parseResultFromModelToViewData(model: $0)})
    }
    
    /// Function for parse Model to ViewData in ResultModel
    /// - Parameter model: ResultsModel
    private func parseResultFromModelToViewData(model: ResultsModel) -> ResultViewData {
        var viewData = ResultViewData()
        viewData.destinationName = "\(model.address?.state ?? "") - \(model.address?.country ?? "")"
        viewData.offerName = model.name ?? ""
        model.amenities?.forEach({viewData.amenities.append($0.name ?? "")})
        viewData.oldPrice = String(model.price?.oldPrice ?? 0.0)
        viewData.newPrice = String(model.price?.amountPerDay ?? 0.0)
        viewData.urlImageCard = model.image ?? model.gallery?.first?.url ?? ""
        model.gallery?.forEach({viewData.gallery.append($0.url ?? "")})
        viewData.freeCancellation = model.huFreeCancellation ?? false
        return viewData
    }
}
