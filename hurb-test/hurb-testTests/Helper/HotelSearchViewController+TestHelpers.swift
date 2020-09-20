//
//  HotelSearchViewController+TestHelpers.swift
//  hurb-testTests
//
//  Created by Tulio Parreiras on 20/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import UIKit
import HotelSearchiOS

extension HotelSearchViewController {
    
    var isShowingLoadingIndicator: Bool {
        return self.spinner.isAnimating
    }
    
    func simulateHotelSearch() {
        self.btnSearch.sendActions(for: .touchUpInside)
    }
    
    func simulateTextInputReturn() {
        _ = self.textField.delegate?.textFieldShouldReturn?(self.textField)
    }
    
    func numberOfRenderedHotelCells(inSection section: Int = 0) -> Int {
        return tableView.numberOfRows(inSection: section)
    }
    
    func hotelCell(at row: Int, section: Int) -> UITableViewCell? {
        guard numberOfRenderedHotelCells(inSection: section) > row else {
            return nil
        }
        let ds = tableView.dataSource
        let index = IndexPath(row: row, section: section)
        return ds?.tableView(tableView, cellForRowAt: index)
    }
    
    func headerView(at section: Int) -> UIView? {
        guard tableView.numberOfSections > section else {
            return nil
        }
        let delegate = tableView.delegate
        return delegate?.tableView?(tableView, viewForHeaderInSection: section)
    }
    
    @discardableResult
    func simulateHotelCellVisible(at row: Int, section: Int) -> HotelCell? {
        return hotelCell(at: row, section: section) as? HotelCell
    }
    
    @discardableResult
    func simulateHotelCellNotVisible(at row: Int, section: Int) -> HotelCell? {
        let view = simulateHotelCellVisible(at: row, section: section)
        
        let delegate = tableView.delegate
        let index = IndexPath(row: row, section: section)
        delegate?.tableView?(tableView, didEndDisplaying: view!, forRowAt: index)
        
        return view
    }
    
    func simulateHotelCellNearVisible(at row: Int, section: Int) {
        let ds = tableView.prefetchDataSource
        let index = IndexPath(row: row, section: section)
        ds?.tableView(tableView, prefetchRowsAt: [index])
    }
    
    func simulateHotelCellNotNearVisible(at row: Int, section: Int) {
        simulateHotelCellNearVisible(at: row, section: section)
        
        let ds = tableView.prefetchDataSource
        let index = IndexPath(row: row, section: section)
        ds?.tableView?(tableView, cancelPrefetchingForRowsAt: [index])
    }
    
}
