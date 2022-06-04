//
//  LastSeenContainerView.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 02/06/22.
//

import Foundation
import UIKit

class LastSeenContainerView: UIView {
    
    // MARK: Properties
    var lastSeenHotels: [LastSeenHotel]? {
        didSet {
            lastSeenTableView.reloadData()
        }
    }
    
    let tableViewCellReuseIdentifier = String(describing: LastSeenTableViewCell.self)
    
    let lastSeenTableView: UITableView = {
        let tableView = UITableView()
        tableView.rowHeight = UITableView.automaticDimension
        return tableView
    }()
    
    // MARK: Lifecycle
    override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: Helper Methods
}

// MARK: CodeView
extension LastSeenContainerView: CodeView {
    func buildViewHierarchy() {
        addSubview(lastSeenTableView)
    }
    
    func setupConstraints() {
        lastSeenTableView.anchor(top: topAnchor,
                                 leading: leadingAnchor,
                                 bottom: bottomAnchor,
                                 trailling: trailingAnchor)
    }
    
    func setupAdditionalConfiguration() {
        /// Configure View.
        backgroundColor = .white
        
        /// Configure TableView.
        lastSeenTableView.register(LastSeenTableViewCell.self, forCellReuseIdentifier: tableViewCellReuseIdentifier)
        lastSeenTableView.delegate = self
        lastSeenTableView.dataSource = self
    }
}
