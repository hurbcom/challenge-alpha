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
    public var query = ""
    public var filter = TypeFilter.hotel
    public var page = 1
    
    // MARK: IBACTIONS
    @IBAction func changeFilterAction(_ sender: UISegmentedControl) {
        let filter = sender.selectedSegmentIndex == 0 ? TypeFilter.hotel : TypeFilter.package
        self.presenter.getOffers(query: self.query, filter: filter)
    }
}

// MARK: - LIFE CYCLE -
extension HotelAndPackageListViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        self.presenter = HotelAndPackageListPresenter(viewDelegate: self, service: HotelPackageService())
        self.registerNIB()
        self.presenter.getOffers(query: self.query, filter: filter)
    }
}

// MARK: - DATASOURCE TABLEVIEW -
extension HotelAndPackageListViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.viewData.list.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell: ElementTableViewCell = self.tableView.dequeueReusableCell(for: indexPath)
        cell.prepareCell(viewData: self.viewData.list[indexPath.row])
        return cell
    }
}

// MARK: - DELEGATE TABLEVIEW -
extension HotelAndPackageListViewController: UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, willDisplay cell: UITableViewCell, forRowAt indexPath: IndexPath) {
        if indexPath.row == self.viewData.list.count - 10, self.viewData.totalPages != self.viewData.currentPage {
            self.viewData.currentPage += 1
            self.presenter.getOffers(for: self.viewData.currentPage, query: self.query, filter: self.filter)
        }
    }
}

// MARK: - DELEGATE PRESENTER -
extension HotelAndPackageListViewController: HotelAndPackageListViewDelegate {
    func startLoading() {
        UIView.animate(withDuration: 0.2) { [weak self] in
            self?.loadingView.isHidden = false
            self?.tableView.isHidden = true
            self?.loadingView.animation = Animation.named("loader")
            self?.loadingView.play()
            self?.loadingView.loopMode = .loop
        }
    }
    
    func stopLoading() {
        UIView.animate(withDuration: 0.2) { [weak self] in
            self?.loadingView.isHidden = true
            self?.tableView.isHidden = false
            self?.loadingView.pause()
        }
    }
    
    func showError(_ error: ErrorType) {
        print(error)
    }
    
    func setViewData(viewData: HotelAndPackageListViewData) {
        self.viewData = viewData
        self.tableView.reloadData()
    }
    
    func setViewDataOfNextPage(viewData: [ResultViewData]) {
        self.viewData.list += viewData
        self.tableView.reloadData()
    }
}

// MARK: - AUX METHODS -
extension HotelAndPackageListViewController {
    private func registerNIB() {
        self.tableView.register(ElementTableViewCell.self)
    }
}
