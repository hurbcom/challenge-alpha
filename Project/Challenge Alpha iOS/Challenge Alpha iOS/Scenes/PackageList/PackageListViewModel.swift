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
    @Published var showLoading = true
    @Published var showError: Bool = false
    
    // MARK: - Dispose bag
    var cancellables: Set<AnyCancellable> = .init()
    
    // MARK: - Dependencies
    var interactor: PackageListInteractorProtocol
    var router: PackageListRouterProtocol
    
    init(interactor: PackageListInteractorProtocol, router: PackageListRouterProtocol) {
        self.interactor = interactor
        self.router = router
    }
    
    func getPackages() {
        interactor.getPackages(
            query: "Rio de Janeiro, Brasil",
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
            self.packages = packages
        }.store(in: &cancellables)
    }
    
    // MARK: View Actions
    func onPackageTap(_ selectedPackage: PackageResult) {
        router.navigateToPackageDetails(selectedPackage)
    }
    
    @objc func onSearchTap() {
        router.presentSearch()
    }
    
    // MARK: - Helpers
    // TODO: Move Model related functions to Model struct
    func getImagesURLForPackage(at index: Int) -> [String] {
        let packageResult: PackageResult = self.packages[index]
        guard let gallery = packageResult.gallery else {
            // TODO: Add log
            return []
        }
        
        let urls = gallery.map { item -> String in
            return item.url ?? ""
        }
        
        return urls
    }
    
    func getTagsForPackage(at index: Int) -> [String] {
        let packageResult: PackageResult = self.packages[index]
        guard let tags = packageResult.tags else {
            // TODO: Add log
            return []
        }
        
        var tagStrings = tags.map { item -> String in
            return item.label ?? ""
        }
        
        if let amount = packageResult.price?.amount, let originalAmount = packageResult.price?.originalAmount,
           amount != originalAmount {
            let discount = 100 - (((originalAmount / 100) / ( amount / 100)) * 100)
            let discountLabel = String(format: "-%.0f", discount) + "%"
            tagStrings.insert(discountLabel, at: 0)
        }
        
        return tagStrings
    }
    
    private func hideLoading() {
        self.showLoading = false
    }
}
