//
//  HotelAndPackageListViewController.swift
//  GPSurb
//
//  Created by Gilson Santos on 17/10/19.
//  Copyright (c) 2019 Gilson Santos. All rights reserved.
//

import UIKit
import Lottie

class HotelAndPackageListViewController: UIViewController {
    
    // MARK: OUTLETS
    @IBOutlet weak var segmentedControl: UISegmentedControl!
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var loadingView: AnimationView!
    // MARK: CONSTANTS
    
    // MARK: VARIABLES
    private var presenter: HotelAndPackageListPresenter!
    private lazy var viewData: HotelAndPackageListViewData = HotelAndPackageListViewData()
    
    // MARK: IBACTIONS
    @IBAction func changeFilterAction(_ sender: UISegmentedControl) {
        print(sender)
    }
    
}

// MARK: - LIFE CYCLE -
extension HotelAndPackageListViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        self.presenter = HotelAndPackageListPresenter(viewDelegate: self)
    }
}

// MARK: - DATASOURCE TABLEVIEW -
extension HotelAndPackageListViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        return UITableViewCell()
    }
    

}

// MARK: - DELEGATE PRESENTER -
extension HotelAndPackageListViewController: HotelAndPackageListViewDelegate {

}

// MARK: - AUX METHODS -
extension HotelAndPackageListViewController {

}
