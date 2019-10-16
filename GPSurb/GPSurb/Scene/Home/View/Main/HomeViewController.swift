//
//  HomeViewController.swift
//  GPSurb
//
//  Created by Gilson Santos on 16/10/19.
//  Copyright (c) 2019 Gilson Santos. All rights reserved.
//

import UIKit
import Lottie

class HomeViewController: UIViewController {
    
    // MARK: OUTLETS
    @IBOutlet weak var collectionView: UICollectionView!
    @IBOutlet weak var loadingView: AnimationView!
    @IBOutlet weak var messageLabel: UILabel!
    // MARK: CONSTANTS
    private let segueList = "showList"
    // MARK: VARIABLES
    private var presenter: HomePresenter!
    private lazy var viewData: HomeViewData = HomeViewData()
    
    // MARK: IBACTIONS
}

// MARK: - LIFE CYCLE -
extension HomeViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        self.presenter = HomePresenter(viewDelegate: self, service: BestDestinationService())
        self.registerNIB()
        self.presenter.getBestDestinations()
    }
}

// MARK: - DELEGATE PRESENTER -
extension HomeViewController: HomeViewDelegate {
    func startLoading() {
        UIView.animate(withDuration: 0.2) { [weak self] in
            self?.loadingView.isHidden = false
            self?.collectionView.isHidden = true
            self?.messageLabel.isHidden = true
            self?.loadingView.animation = Animation.named("loader")
            self?.loadingView.play()
            self?.loadingView.loopMode = .loop
        }
    }
    
    func stopLoading() {
        UIView.animate(withDuration: 0.2) { [weak self] in
            self?.loadingView.isHidden = true
            self?.collectionView.isHidden = false
            self?.messageLabel.isHidden = false
            self?.loadingView.pause()
        }
    }
    
    func showError() {
        
    }
    
    func setViewData(viewData: HomeViewData) {
        self.viewData = viewData
        self.collectionView.reloadData()
    }
}

// MARK: - COLLECTIONVIEW DATASOURCE -
extension HomeViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.viewData.bestDestinations.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let itemName = "BestDestinationsCollectionViewCell"
        guard let item = collectionView.dequeueReusableCell(withReuseIdentifier: itemName, for: indexPath) as?
              BestDestinationsCollectionViewCell
              else { return UICollectionViewCell() }
        item.setupItem(viewData: self.viewData.bestDestinations[indexPath.row])
        self.collectionViewItemAnimation(itemView: item.contentView,
                                         index: indexPath.row,
                                         itemHeight: item.contentView.bounds.height)
        return item
    }
    
}

// MARK: - COLLECTIONVIEW FLOWLAYOUT DELEGATE -
extension HomeViewController: UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let frameWidth = self.view.frame.width
        let frameHeight = self.view.frame.height
        return CGSize(width: frameWidth * 0.70, height: frameHeight * 0.56)
    }
}

// MARK: - COLLECTIONVIEW DELEGATE -
extension HomeViewController: UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        self.performSegue(withIdentifier: self.segueList, sender: indexPath.row)
    }
}

// MARK: - AUX METHODS -
extension HomeViewController {
    private func registerNIB() {
        self.collectionView.register(BestDestinationsCollectionViewCell.self)
    }
    
    private func collectionViewItemAnimation(itemView: UIView, index: Int, itemHeight: CGFloat) {
        itemView.transform = CGAffineTransform(translationX: 0, y: itemHeight / 2)
        itemView.alpha = 0
        UIView.animate(
            withDuration: 0.4,
            delay: 0.05 * Double(index),
            options: [.curveEaseInOut],
            animations: {
                itemView.transform = CGAffineTransform(translationX: 0, y: 0)
                itemView.alpha = 1
        })
    }
}
