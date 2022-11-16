//
//  SuggestionsViewTest.swift
//  Challenge_iOSTests
//
//  Created by Helio Junior on 15/11/22.
//

import XCTest
@testable import Challenge_iOS

class SuggestionsViewTest: XCTestCase {
    
    var sut: SuggestionsView!

    override func setUpWithError() throws {
        sut = SuggestionsView.fromNib()
        _ = sut
    }

    override func tearDownWithError() throws {
        sut = nil
    }
    
    func test_TableviewSuccessRenderCells() {
        let suggestions = [
            SuggestionModel(text: "Rio de Janeiro"),
            SuggestionModel(text: "Rio Claro"),
            SuggestionModel(text: "Rio Negro"),
            SuggestionModel(text: "Rio Branco")
        ]
        sut.setup(with: suggestions)
        
        XCTAssertEqual(sut.tableView.numberOfRows(inSection: 0), 4)
    }
    
    func test_TableViewRenderCellsAndIdentifierValueCell() {
        let suggestions = [
            SuggestionModel(text: "Rio de Janeiro"),
            SuggestionModel(text: "Rio Claro"),
            SuggestionModel(text: "Rio Negro"),
            SuggestionModel(text: "Rio Branco")
        ]
        sut.setup(with: suggestions)
        
        let indexPath = IndexPath(row: 0, section: 0)
        let tableView = sut.tableView!
        
        let cell = sut.tableView(tableView, cellForRowAt: indexPath)
        XCTAssertEqual(cell.textLabel?.text, "Rio de Janeiro")
    }
}
