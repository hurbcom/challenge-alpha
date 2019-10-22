//
//  ListViewController.swift
//  desafio-hurb-ios
//
//  Created by Vinícius Barcelos on 21/10/19.
//  Copyright © 2019 Vinícius Barcelos. All rights reserved.
//

import UIKit

protocol ListViewProtocol {
    func setupView()->Void
}

class ListViewController: UIViewController {

    @IBOutlet weak var collectionView: UICollectionView!
    @IBOutlet weak var activityIndicatorView: UIActivityIndicatorView!
    
    var viewModel: ListViewModel!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.viewModel = ListViewModel(view: self)
        self.viewModel.getHotels(with: "buzios")
        self.setup()
        self.customize()
        self.setupCollectionView()
    }
}

// MARK: - Setup Methods

extension ListViewController {
    
    func setup() {
        self.title = "Hotéis"
        self.collectionView.hide()
        self.activityIndicatorView.stopAnimating()
    }
    
    func customize() {
        self.view.backgroundColor = Theme.backgroundColor
        self.collectionView.backgroundColor = Theme.backgroundColor
    }
    
}


// MARK: - Collection View Methods

extension ListViewController: UICollectionViewDelegate, UICollectionViewDataSource, UICollectionViewDelegateFlowLayout {
    
    func setupCollectionView() {
        self.collectionView.delegate = self
        self.collectionView.dataSource = self
        self.collectionView.register(UINib(nibName: ListHotelCollectionViewCell.identifier, bundle: nil), forCellWithReuseIdentifier: ListHotelCollectionViewCell.identifier)
    }
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return self.viewModel.sortedHotels.count
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.viewModel.sortedHotels[section].count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: ListHotelCollectionViewCell.identifier, for: indexPath) as! ListHotelCollectionViewCell
        cell.config(with: self.viewModel.sortedHotels[indexPath.section][indexPath.row])//[indexPath.row])
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let width = self.collectionView.frame.width
        return CGSize(width: width, height: 130)
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int) -> UIEdgeInsets {
        return UIEdgeInsets(top: 16, left: 0, bottom: 16, right: 0)
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumLineSpacingForSectionAt section: Int) -> CGFloat {
        return 16
    }
    
}


// MARK: - Protocol Methods

extension ListViewController: ListViewProtocol {
    
    func setupView() {
        
        self.activityIndicatorView.stopAnimating()
        self.collectionView.hide()
        
        switch self.viewModel.status {
        case .loading:
            self.activityIndicatorView.startAnimating()
        case .loaded:
            self.collectionView.reloadData()
            self.collectionView.show()
        default:
            print("default")
        }
        
    }
    
}
