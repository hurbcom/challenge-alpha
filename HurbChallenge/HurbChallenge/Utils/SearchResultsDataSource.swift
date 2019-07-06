//
//  SearchResultsDataSource.swift
//  HurbChallenge
//
//  Created by Felipe Alves on 06/07/19.
//  Copyright © 2019 Bolzaniapps. All rights reserved.
//

import Foundation
import Promises

struct SearchResultsDataSource {
    
    private let sections = ["5 estrelas", "4 estrelas", "3 estrelas", "2 estrelas", "1 estrelas", "Tickets", "Pacotes"]
    private var elements: [[SearchResultElement]] = [[], [], [], [], [], [], []]
    
    mutating func update(with list: [SearchResultElement]) -> Promise<[IndexPath]> {
        return Promise<[IndexPath]> { resolve, reject in
            var indexPaths: [IndexPath] = []
            list.forEach { item in
                var section: Int
                if let stars = item.stars {
                    section = 5 - stars
                } else if let isTicket = item.isTicket, isTicket == true {
                    section = 5
                } else {
                    section = 6
                }
                self.elements[section].append(item)
                
                let indexPath = IndexPath(row: self.numberOfItems(in: section) - 1, section: section)
                indexPaths.append(indexPath)
            }
            resolve(indexPaths)
        }
    }
    
    func hasItems() -> Bool {
        return elements.filter({$0.count > 0}).count > 0
    }
    
    func title(for section: Int) -> String? {
        return (numberOfSections() > section && numberOfItems(in: section) > 0) ? sections[section] : nil
    }
    
    func numberOfSections() -> Int {
        return sections.count
    }
    
    func numberOfItems(in section: Int) -> Int {
        return elements[section].count
    }
    
    func item(in indexPath: IndexPath) -> SearchResultElement? {
        guard numberOfItems(in: indexPath.section) > indexPath.row else { return nil }
        return elements[indexPath.section][indexPath.row]
    }
}
