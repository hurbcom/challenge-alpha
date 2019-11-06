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
    
    // MARK: - Init
    init(with sections: [FeedSection]) {
        self.sections = sections
    }
    
    // MARK: - TableView Methods
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return sections.count
    }
    
    func tableView(_ tableView: UITableView, willDisplayHeaderView view: UIView, forSection section: Int) {
        if section == 0 {
            view.tintColor = .hurbYellow
        } else {
            let header = view as! UITableViewHeaderFooterView
            header.textLabel?.textColor = .white
            view.tintColor = .hurbBlue
        }
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

