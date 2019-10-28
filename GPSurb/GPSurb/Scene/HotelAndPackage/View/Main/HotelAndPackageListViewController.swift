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
    @IBOutlet weak var errorView: AnimationView!
    // MARK: CONSTANTS
    private let segueDetail = "segueDetail"
    // MARK: VARIABLES
    private var presenter: HotelAndPackageListPresenter!
    private lazy var viewData = HotelAndPackageListViewData()
    public var query = ""
    public var filter = TypeFilter.hotel
    public var page = 1
    
    // MARK: IBACTIONS
    @IBAction func changeFilterAction(_ sender: UISegmentedControl) {
        let filter = sender.selectedSegmentIndex == 0 ? TypeFilter.hotel : TypeFilter.package
        self.presenter.getOffers(query: self.query, filter: filter)
    }
    
    @objc private func reload() {
        self.presenter.getOffers(query: self.query, filter: filter)
    }
}

// MARK: - LIFE CYCLE -
extension HotelAndPackageListViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        self.presenter = HotelAndPackageListPresenter(viewDelegate: self, service: HotelPackageService())
        self.registerNIB()
        self.addGesture()
        self.presenter.getOffers(query: self.query, filter: filter)
        self.setupView()
    }
}

// MARK: - DATASOURCE TABLEVIEW -
extension HotelAndPackageListViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.viewData.list.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell: ElementTableViewCell = self.tableView.dequeueReusableCell(for: indexPath)
        cell.delegate = self
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
            self?.errorView.isHidden = true
            self?.loadingView.animation = Animation.named("loader")
            self?.loadingView.play()
            self?.loadingView.loopMode = .loop
        }
    }
    
    func stopLoading() {
        UIView.animate(withDuration: 0.2) { [weak self] in
            self?.loadingView.isHidden = true
            self?.loadingView.pause()
        }
    }
    
    func showError(_ error: ErrorType) {
        self.errorView.animation = Animation.named("error")
        self.errorView.play()
        self.errorView.loopMode = .loop
        self.errorView.isHidden = false
        self.loadingView.isHidden = true
        self.tableView.isHidden = true
    }
    
    func setViewData(viewData: HotelAndPackageListViewData) {
        self.viewData = viewData
        self.tableView.isHidden = false
        self.errorView.isHidden = true
        self.tableView.reloadData()
    }
    
    func setViewDataOfNextPage(viewData: [ResultViewData]) {
        self.viewData.list += viewData
        self.tableView.reloadData()
    }
}

// MARK: - DELEGATE ELEMENTDELEGATE -
extension HotelAndPackageListViewController: ElementDelegate {
    func showDetail(viewData: ResultViewData) {
        self.performSegue(withIdentifier: self.segueDetail, sender: viewData)
    }
}

// MARK: - AUX METHODS -
extension HotelAndPackageListViewController {
    private func registerNIB() {
        self.tableView.register(ElementTableViewCell.self)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let viewController = segue.destination as? DetailViewController, let viewData = sender as? ResultViewData {
            viewController.viewData = viewData
        }
    }
    
    private func addGesture() {
        let tap = UITapGestureRecognizer(target: self, action: #selector(self.reload))
        self.errorView.addGestureRecognizer(tap)
    }
    
    private func setupView() {
        self.segmentedControl.accessibilityIdentifier = "segment-identifier"
        self.tableView.accessibilityIdentifier = "tableViewList-identifier"
        self.errorView.accessibilityIdentifier = "errorView-identifier"
    }
}
