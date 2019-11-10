//
//  FeedViewController.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 09/11/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import UIKit

class FeedViewController: UIViewController, StoryboardInitializable {
    
    static var storyboardName: String = "FeedScreen"
    
    static var storyboardID: String = "FeedViewController"
    
    var dataSource: FeedDataSource? {
        didSet {
            feedTableView.dataSource = self.dataSource
            self.feedTableView.reloadData()
        }
    }
    
    var coordinator: MainCoordinator?
    
    var feedTableView: UITableView = {
        let view = UITableView()
        view.backgroundColor = .clear
        view.register(FeedTableViewCell.self, forCellReuseIdentifier: "feedTableViewCell")
        view.register(FeedHeader.self, forHeaderFooterViewReuseIdentifier: "FeedHeader")
        view.tableFooterView = UIView(frame: .zero)
        view.isScrollEnabled = true
        view.showsVerticalScrollIndicator = false
        view.rowHeight = UIScreen.main.bounds.height/2
        view.sectionHeaderHeight = UITableView.automaticDimension
        view.estimatedRowHeight = UIScreen.main.bounds.height/2
        view.estimatedSectionHeaderHeight = UIScreen.main.bounds.height/10
        view.layoutMargins = .zero
        view.accessibilityIdentifier = "feedTableView"
        return view
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        feedTableView.delegate = self
        self.view.addSubview(feedTableView)
        setUpConstraints()
    }
    
    func setUpConstraints() {
        feedTableView.snp.makeConstraints { (make) in
            make.top.equalTo(view.safeAreaLayoutGuide.snp.top).offset(20)
            make.leading.equalTo(view.safeAreaLayoutGuide.snp.leadingMargin)
            make.bottom.equalTo(view.safeAreaLayoutGuide.snp.bottom)
            make.trailing.equalTo(view.safeAreaLayoutGuide.snp.trailingMargin)
        }
    }

}

extension FeedViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        guard let header = tableView.dequeueReusableHeaderFooterView(withIdentifier: "FeedHeader") as? FeedHeader else {
            fatalError("Feed View Controller: could not use header view")
        }
        guard dataSource != nil else { return header }
        if dataSource!.hasPackages() && section == 0 {
            header.starsNumberLabel.text = String(describing: "Pacotes")
        } else {
            let starsNumber = dataSource!.hasPackages() ? dataSource?.hotelsStarsIndex[(section - 1)] : dataSource?.hotelsStarsIndex[(section)]
            header.starsNumberLabel.text = "\(starsNumber!) estrelas"
        }
        return header
    }
    
}
