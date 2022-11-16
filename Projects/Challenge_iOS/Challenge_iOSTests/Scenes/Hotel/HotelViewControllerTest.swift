//
//  HotelViewControllerTest.swift
//  Challenge_iOSTests
//
//  Created by Helio Junior on 15/11/22.
//

import XCTest
@testable import Challenge_iOS

class HotelViewControllerTest: XCTestCase {
    
    var sut: HotelViewController!
    var viewModel: HotelViewModel!
    
    override func setUpWithError() throws {
        let serviceStub = HotelServiceSuccessStub()
        viewModel = HotelViewModel(service: serviceStub)
        sut = HotelViewController(viewModel: viewModel)
        _ = sut.view
    }

    override func tearDownWithError() throws {
        sut = nil
        viewModel = nil
    }
    
    func test_TableViewSuccessRenderCells() {
        viewModel.findHotelFrom(query: "")
        
        XCTAssertEqual(sut.tableView.numberOfRows(inSection: 0), 2)
    }
    
    func test_TableViewFailureRenderCells() {
        let serviceStub = HotelServiceFailureStub()
        viewModel = HotelViewModel(service: serviceStub)
        sut = HotelViewController(viewModel: viewModel)
        _ = sut.view
        
        viewModel.findHotelFrom(query: "")
        
        XCTAssertEqual(sut.tableView.numberOfRows(inSection: 0), 0)
    }

    func test_TableViewRenderCellsAndIdentifierHotelCell() {
        viewModel.findHotelFrom(query: "")
        let indexPath = IndexPath(row: 0, section: 0)
        let tableView = sut.tableView!
        
        if let cell = sut.tableView(tableView, cellForRowAt: indexPath) as? CardHotelTableViewCell {
            XCTAssertEqual(cell.labelName.text, "Imperial Plaza Hotel")
            XCTAssertEqual(cell.labelAddress.text, "Brasília, Brasil")
            XCTAssertEqual(cell.labelValue.text, "R$ 158")
        }
    }
    
    func test_TableViewRenderCellsAndIdentifierPackageCell() {
        viewModel.findHotelFrom(query: "")
        let indexPath = IndexPath(row: 1, section: 0)
        let tableView = sut.tableView!
        
        if let cell = sut.tableView(tableView, cellForRowAt: indexPath) as? CardPackageTableViewCell {
            XCTAssertEqual(cell.labelName.text, "Pacote de Viagem - CDesign Hotel (Rio de Janeiro) - 2023")
            XCTAssertEqual(cell.labelDailys.text, "1 diária")
            XCTAssertEqual(cell.labelPersons.text, "2 pessoas")
        }
    }
    
    func test_ShowViewSearchSuggestions() {
        let searchBar = sut.searchController.searchBar
        _ = sut.searchController.searchBar.delegate?.searchBarShouldBeginEditing?(searchBar)

        XCTAssertFalse(sut.viewSearchSuggestions.isHidden)
    }
    
    func test_ShowAndAfterHideViewSearchSuggestions() {
        let searchBar = sut.searchController.searchBar
        _ = sut.searchController.searchBar.delegate?.searchBarShouldBeginEditing?(searchBar)
        sut.searchController.searchBar.delegate?.searchBarTextDidEndEditing?(searchBar)

        XCTAssertTrue(sut.viewSearchSuggestions.isHidden)
    }
    
    func test_HideViewSearchSuggestionsIfClickedButtonCancel() {
        let searchBar = sut.searchController.searchBar
        _ = sut.searchController.searchBar.delegate?.searchBarShouldBeginEditing?(searchBar)
        sut.searchController.searchBar.delegate?.searchBarCancelButtonClicked?(searchBar)
        
        XCTAssertTrue(sut.viewSearchSuggestions.isHidden)
    }
    
    func test_ShowViewSearchNotFound() {
        let serviceStub = HotelServiceFailureStub()
        viewModel = HotelViewModel(service: serviceStub)
        sut = HotelViewController(viewModel: viewModel)
        _ = sut.view
        
        viewModel.findHotelFrom(query: "")
        
        XCTAssertFalse(sut.viewSearchNotFound.isHidden)
    }
    
    func test_SearchFromClickedButtonSearch() {
        let searchBar = sut.searchController.searchBar
        _ = sut.searchController.searchBar.delegate?.searchBarShouldBeginEditing?(searchBar)
        sut.searchController.searchBar.delegate?.searchBar?(searchBar, textDidChange: "Rio de Janeiro")
        sut.searchController.searchBar.delegate?.searchBarSearchButtonClicked?(searchBar)

        XCTAssertEqual(sut.tableView.numberOfRows(inSection: 0), 2)
    }
    
    func test_SearchFromClickedSuggestion() {
        let searchBar = sut.searchController.searchBar
        _ = sut.searchController.searchBar.delegate?.searchBarShouldBeginEditing?(searchBar)
        sut.searchController.searchBar.delegate?.searchBar?(searchBar, textDidChange: "Rio de Janeiro")
        
        sut.viewSearchSuggestions.didSelectedSuggestion?(SuggestionModel(text: "Rio de Janeiro"))

        XCTAssertEqual(sut.tableView.numberOfRows(inSection: 0), 2)
    }
}
