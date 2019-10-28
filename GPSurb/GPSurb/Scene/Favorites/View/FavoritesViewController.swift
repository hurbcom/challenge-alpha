//
//  FavoritesViewController.swift
//  GPSurb
//
//  Created by Gilson Santos on 14/10/19.
//  Copyright (c) 2019 Gilson Santos. All rights reserved.
//

import UIKit
import Lottie

class FavoritesViewController: UIViewController {
    
    // MARK: OUTLETS
    @IBOutlet weak var tableViewFavorite: UITableView!
    @IBOutlet weak var viewEmpty: AnimationView!
    // MARK: CONSTANTS
    private let segueDetail = "segueDetail"
    // MARK: VARIABLES
    private var presenter: FavoritesPresenter!
    private lazy var viewData = [ResultViewData]()
    // MARK: IBACTIONS
}

// MARK: - LIFE CYCLE -
extension FavoritesViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        self.presenter = FavoritesPresenter(viewDelegate: self)
        self.registerNIB()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.presenter.getFavoriteInDataBase()
    }
}

// MARK: - DATASOURCE TABLEVIEW -
extension FavoritesViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.viewData.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell: ElementTableViewCell = self.tableViewFavorite.dequeueReusableCell(for: indexPath)
        cell.delegate = self
        cell.prepareCell(viewData: self.viewData[indexPath.row])
        return cell
    }
}

// MARK: - DELEGATE PRESENTER -
extension FavoritesViewController: FavoritesViewDelegate {
    func showEmpty() {
        self.showEmptyView()
    }
    
    func setViewData(viewData: [ResultViewData]) {
        self.viewData = viewData
        self.viewEmpty.isHidden = true
        self.tableViewFavorite.reloadData()
    }
}

// MARK: - DELEGATE ELEMENTDELEGATE -
extension FavoritesViewController: ElementDelegate {
    func showDetail(viewData: ResultViewData) {
        self.performSegue(withIdentifier: self.segueDetail, sender: viewData)
    }
}

// MARK: - AUX METHODS -
extension FavoritesViewController {
    private func registerNIB() {
        self.tableViewFavorite.register(ElementTableViewCell.self)
    }
    
    private func showEmptyView() {
        self.viewEmpty.animation = Animation.named("empty")
        self.viewEmpty.play()
        self.viewEmpty.loopMode = .loop
        self.viewEmpty.isHidden = false
        self.viewData.removeAll()
        self.tableViewFavorite.reloadData()
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let viewController = segue.destination as? DetailViewController, let viewData = sender as? ResultViewData {
            viewController.viewData = viewData
        }
    }
}
