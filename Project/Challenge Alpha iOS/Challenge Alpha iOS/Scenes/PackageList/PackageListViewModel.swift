//
//  PackageListViewModel.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 16/01/23.
//

import Foundation
import Combine

final class PackageListViewModel: ObservableObject {
    // MARK: - Published variables
    @Published var packages: [PackageResult] = []
    @Published var query: String
    @Published var showLoading = true
    @Published var showError: Bool = false
    
    // MARK: - Dispose bag
    var cancellables: Set<AnyCancellable> = .init()
    
    // MARK: - Dependencies
    var interactor: PackageListInteractorProtocol
    var router: PackageListRouterProtocol
    
    init(query: String = Constants.DEFAULT_DESTINATION, interactor: PackageListInteractorProtocol, router: PackageListRouterProtocol = PackageListRouter()) {
        self.query = query
        self.interactor = interactor
        self.router = router
    }
    
    func getPackages() {
        interactor.getPackages(
            query: self.query,
            pagination: .init(
                page: 1,
                limit: 20,
                sort: .price,
                sortOrder: .desc)
        )
        .receive(on: DispatchQueue.main)
        .sink { completion in
            if case .failure(_) = completion {
                // Handle error
                self.showError = true
            }
            
            self.hideLoading()
        } receiveValue: { packages in
            if packages.isEmpty {
                self.showError = true
            }
            
            self.packages = packages
        }.store(in: &cancellables)
    }
    
    // MARK: View Actions
    func onPackageTap(_ selectedPackage: PackageResult) {
        router.navigateToPackageDetails(selectedPackage)
    }
    
    @objc func onSearchTap() {
        router.presentSearch(onComplete: self.redraw(with:))
    }
    
    // MARK: - Helpers
    private func hideLoading() {
        self.showLoading = false
    }
    
    func redraw(with searchTerm: String) {
        self.showLoading = true
        self.query = searchTerm
    }
}
