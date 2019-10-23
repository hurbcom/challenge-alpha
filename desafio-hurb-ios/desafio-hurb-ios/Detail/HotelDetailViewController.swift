//
//  HotelDetailViewController.swift
//  desafio-hurb-ios
//
//  Created by Vinícius Barcelos on 22/10/19.
//  Copyright © 2019 Vinícius Barcelos. All rights reserved.
//

import UIKit

class HotelDetailViewController: UIViewController {
    
    @IBOutlet weak var tableView: UITableView!
    
    var selectedHotel: Hotel?
    
    let headerImageView = UIImageView()
    lazy var headerImageViewHeight: CGFloat = {
        return self.view.frame.width * 0.75
    }()


    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        self.setupTableView()
        self.setup()
        self.setupHeaderImageView()
        self.tableView.reloadData()
    }
}


// MARK: - Setup Methods

extension HotelDetailViewController {
    func setup() {
        self.title = selectedHotel?.name
    }
}


// MARK: - Table View Methods

extension HotelDetailViewController: UITableViewDelegate, UITableViewDataSource {
    
    func setupTableView() {
        self.tableView.delegate = self
        self.tableView.dataSource = self
        self.tableView.separatorStyle = .none
        self.tableView.register(UINib(nibName: HotelDetailTableViewCell.identifier, bundle: nil), forCellReuseIdentifier: HotelDetailTableViewCell.identifier)
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: HotelDetailTableViewCell.identifier, for: indexPath) as! HotelDetailTableViewCell
        if let hotel = selectedHotel {
            cell.configCell(with: hotel)
        }
        return cell
    }
    
    func setupHeaderImageView() {
        self.tableView.contentInset = UIEdgeInsets(top: headerImageViewHeight, left: 0, bottom: 0, right: 0)
        self.headerImageView.frame = CGRect(x: 0, y: 0, width: UIScreen.main.bounds.size.width, height: headerImageViewHeight)
        if let image = self.selectedHotel?.image {
            self.headerImageView.setImage(with: image, placeholder: "placeholder")
        } else {
            self.headerImageView.image = UIImage(named: "placeholder")
        }
        self.headerImageView.contentMode = .scaleAspectFill
        self.headerImageView.clipsToBounds = true
        self.view.addSubview(headerImageView)
    }
    
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        let y = headerImageViewHeight - (scrollView.contentOffset.y + headerImageViewHeight)
        let height = min(max(y, 0), headerImageViewHeight + 100)
        self.headerImageView.frame = CGRect(x: 0, y: 0, width: UIScreen.main.bounds.size.width, height: height)
    }
    
}
 
