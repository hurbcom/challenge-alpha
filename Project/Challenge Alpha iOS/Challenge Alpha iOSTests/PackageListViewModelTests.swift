//
//  PackageListViewModelTests.swift
//  Challenge Alpha iOSTests
//
//  Created by Yuri Strack on 15/01/23.
//

import XCTest
@testable import Challenge_Alpha_iOS

final class PackageListViewModelTests: XCTestCase {

    func testGetPackageList() throws {
        let viewModel = PackageListViewModel(interactor: PackageListInteractorMock())
        
        viewModel.getPackages()
        
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.1) {
            XCTAssertTrue(!viewModel.packages.isEmpty)
            XCTAssertEqual(viewModel.packages.first?.sku, "LGPKG-1094980-0")
            XCTAssertFalse(viewModel.showLoading)
        }
    }
    
    func testRedraw() throws {
        let viewModel = PackageListViewModel(interactor: PackageListInteractorMock())
        
        viewModel.getPackages()
        viewModel.redraw(with: "São Paulo, Brasil")
        
        XCTAssertEqual(viewModel.query, "São Paulo, Brasil")
    }
}
