//
//  FeedDataSource.swift
//  HurbAlpha
//
//  Created by Rodrigo Bukowitz on 06/11/19.
//  Copyright © 2019 Rodrigo Bukowitz. All rights reserved.
//

import UIKit

class FeedDataSource: NSObject, UITableViewDataSource, UITableViewDelegate {
    var sections: [FeedSection]
    
    // MARK: - Init
    init(with sections: [FeedSection]) {
        self.sections = sections
    }
    
    // MARK: - TableView Methods
    
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return sections[section].title
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return section == 0 ? 30.0 : 40.0
    }
    
    func tableView(_ tableView: UITableView, estimatedHeightForHeaderInSection section: Int) -> CGFloat {
        return 40.0
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return  300.0
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return sections.count
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return sections[section].items.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let item = sections[indexPath.section].items[indexPath.row]
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "offers", for: indexPath) as? HurbTableViewCell else {
            fatalError("Unable to queue the cell")
        }
        
        cell.hurbOffer = item
        
        return cell
    }
    
}
