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
    
    func test_WhenSearchTerm_TableViewSuccessRenderCells() {
        viewModel.findHotelFrom(query: "")
        
        XCTAssertEqual(sut.tableView.numberOfRows(inSection: 0), 2)
    }
    
    func test_WhenSearchTermNotExist_TableViewFailureRenderCells() {
        let serviceStub = HotelServiceFailureStub()
        viewModel = HotelViewModel(service: serviceStub)
        sut = HotelViewController(viewModel: viewModel)
        _ = sut.view
        
        viewModel.findHotelFrom(query: "")
        
        XCTAssertEqual(sut.tableView.numberOfRows(inSection: 0), 0)
    }

    func test_WhenSearchTerm_TableViewRenderCellsAndIdentifierHotelCell() {
        viewModel.findHotelFrom(query: "")
        let indexPath = IndexPath(row: 0, section: 0)
        let tableView = sut.tableView!
        
        if let cell = sut.tableView(tableView, cellForRowAt: indexPath) as? CardHotelTableViewCell {
            XCTAssertEqual(cell.labelName.text, "Imperial Plaza Hotel")
            XCTAssertEqual(cell.labelAddress.text, "Brasília, Brasil")
            XCTAssertEqual(cell.labelValue.text, "R$ 158")
        }
    }
    
    func test_WhenBeginSearch_ShowSearchSuggestionsView() {
        let searchBar = sut.searchController.searchBar
        _ = sut.searchController.searchBar.delegate?.searchBarShouldBeginEditing?(searchBar)

        XCTAssertFalse(sut.viewSearchSuggestions.isHidden)
    }
    
    func test_WhenBeginAndEndSearch_ThenHideSearchSuggestionsView() {
        let searchBar = sut.searchController.searchBar
        _ = sut.searchController.searchBar.delegate?.searchBarShouldBeginEditing?(searchBar)
        sut.searchController.searchBar.delegate?.searchBarTextDidEndEditing?(searchBar)

        XCTAssertTrue(sut.viewSearchSuggestions.isHidden)
    }
    
    func test_WhenClickedButtonCancel_ThenHideSearchSuggestionsView() {
        let searchBar = sut.searchController.searchBar
        _ = sut.searchController.searchBar.delegate?.searchBarShouldBeginEditing?(searchBar)
        sut.searchController.searchBar.delegate?.searchBarCancelButtonClicked?(searchBar)
        
        XCTAssertTrue(sut.viewSearchSuggestions.isHidden)
    }
    
    func test_WhenFailureSearch_ThenShowSearchNotFoundView() {
        let promesse = expectation(description: "View de Resultado não encontrado deve ser exibida.")
        let serviceStub = HotelServiceFailureStub()
        viewModel = HotelViewModel(service: serviceStub)
        sut = HotelViewController(viewModel: viewModel)
        _ = sut.view
        
        viewModel.findHotelFrom(query: "")
        
        DispatchQueue.main.asyncAfter(deadline: .now() + .milliseconds(100)) {
            XCTAssertFalse(self.sut.viewSearchNotFound.isHidden)
            promesse.fulfill()
        }
        wait(for: [promesse], timeout: 1)
    }
    
    func test_WhenClickedOnButtonSearchKeyboard_ThenSearchAndLoadResults() {
        let searchBar = sut.searchController.searchBar
        _ = sut.searchController.searchBar.delegate?.searchBarShouldBeginEditing?(searchBar)
        sut.searchController.searchBar.delegate?.searchBar?(searchBar, textDidChange: "Rio de Janeiro")
        sut.searchController.searchBar.delegate?.searchBarSearchButtonClicked?(searchBar)

        XCTAssertEqual(sut.tableView.numberOfRows(inSection: 0), 2)
    }
    
    func test_WhenClickedOnSuggestion_ThenSearchAndLoadResults() {
        let searchBar = sut.searchController.searchBar
        _ = sut.searchController.searchBar.delegate?.searchBarShouldBeginEditing?(searchBar)
        sut.searchController.searchBar.delegate?.searchBar?(searchBar, textDidChange: "Rio de Janeiro")
        
        sut.viewSearchSuggestions.didSelectedSuggestion?(SuggestionModel(text: "Rio de Janeiro"))

        XCTAssertEqual(sut.tableView.numberOfRows(inSection: 0), 2)
    }
}
