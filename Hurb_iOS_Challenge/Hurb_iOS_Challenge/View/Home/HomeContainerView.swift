//
//  HomeContainerView.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 23/05/22.
//

import UIKit

class HomeContainerView: UIView {
    // MARK: - Properties
    var hotelsResult: [HotelResult]? {
        didSet {
            configureContainter()
        }
    }
    
    let reuseIdentifier = String(describing: HomeCollectionViewCell.self)
    
    lazy var homeCollectionView: UICollectionView = {
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .vertical
        layout.minimumInteritemSpacing = 8
        
        let collectionView = UICollectionView(frame: .zero, collectionViewLayout: layout)
        collectionView.backgroundColor = .clear
        collectionView.isUserInteractionEnabled = true
        collectionView.showsVerticalScrollIndicator = false
        collectionView.dataSource = self
        collectionView.delegate = self
        collectionView.backgroundColor = .white
        return collectionView
    }()
    
    // MARK: - Lifecycle
    override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: - Helper Methods
    
    private func configureContainter() {
        homeCollectionView.reloadData()
    }
}

// MARK: - CodeView
extension HomeContainerView: CodeView {
    func buildViewHierarchy() {
        addSubview(homeCollectionView)
    }
    
    func setupConstraints() {
        homeCollectionView.anchor(top: topAnchor,
                                  leading: leadingAnchor,
                                  bottom: bottomAnchor,
                                  trailling: trailingAnchor)
    }
    
    func setupAdditionalConfiguration() {
        /// Register CollectionView Cell.
        homeCollectionView.register(HomeCollectionViewCell.self, forCellWithReuseIdentifier: reuseIdentifier)
    }
}
