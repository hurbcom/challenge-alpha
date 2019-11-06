//
//  FeedDataSource.swift
//  HurbAlpha
//
//  Created by Rodrigo Bukowitz on 06/11/19.
//  Copyright © 2019 Rodrigo Bukowitz. All rights reserved.
//

import UIKit
import os.log

class FeedDataSource: NSObject, UITableViewDataSource, UITableViewDelegate {
    var sections: [FeedSection]
    
    init(with sections: [FeedSection]) {
        self.sections = sections
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return sections.count
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 40.0
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return  300.0
    }
    
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return sections[section].title
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        let item: FeedSectionType = sections[section].cellData
        
        switch item {
        case .Hotel(let hotels): return hotels.count
        case .Package(let packages): return packages.count
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let item: FeedSectionType = sections[indexPath.section].cellData
        
        switch item {
        case .Hotel(let hotels):
            guard let cell = tableView.dequeueReusableCell(withIdentifier: item.identifier) as? HurbTableViewCell else {
                fatalError("Unknow indentifier")
            }
            cell.hurbOffer = hotels[indexPath.row]
            return cell
        case .Package(let packages):
            guard let cell = tableView.dequeueReusableCell(withIdentifier: item.identifier) as? HurbTableViewCell else {
                fatalError("Unknow indentifier")
            }
            cell.hurbOffer = packages[indexPath.row]
            return cell
        }
    }
}

//class FeedDataSource: NSObject, UITableViewDataSource, UITableViewDelegate {
//    var sections: [FeedSection]
//
//    // MARK: - Init
//    init(with sections: [FeedSection]) {
//        self.sections = sections
//    }
//
//    // MARK: - TableView Methods
//
//    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
//        return sections[section].title
//    }
//
//    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
//        return 40.0
//    }
//
//    func tableView(_ tableView: UITableView, estimatedHeightForHeaderInSection section: Int) -> CGFloat {
//        return 40.0
//    }
//
//    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
//        return  300.0
//    }
//
//    func numberOfSections(in tableView: UITableView) -> Int {
//        return sections.count
//    }
//
//    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
//        return sections[section].items.count
//    }
//
//    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
//        let item = sections[indexPath.section].items[indexPath.row]
//        guard let cell = tableView.dequeueReusableCell(withIdentifier: "offers", for: indexPath) as? HurbTableViewCell else {
//            fatalError("Unable to queue the cell")
//        }
//
//        cell.hurbOffer = item
//
//        return cell
//    }
//
//}
