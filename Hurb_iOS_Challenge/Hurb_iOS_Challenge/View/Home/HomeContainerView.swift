//
//  HomeContainerView.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 23/05/22.
//

import UIKit

class HomeContainerView: UIView {
    // MARK: - Properties
    var parentViewController: HomeViewController?
    
    var hotelsResult: [HotelResult]? {
        didSet {
            configureContainter()
        }
    }
    
    var oneStarHotel: [HotelResult] = []
    var twoStarHotel: [HotelResult] = []
    var threeStarHotel: [HotelResult] = []
    var fourStarHotel: [HotelResult] = []
    var fiveStarHotel: [HotelResult] = []
    
    //let sectionTitles: [String] = ["5 Estrelas", "4 Estrelas", "3 Estrelas", "2 Estrelas", "1 Estrela"]
    
    let reuseIdentifier = String(describing: HomeCollectionViewCell.self)
    let headerIdentifier = "HeaderIdentifier"
    
    lazy var homeCollectionView: UICollectionView = {
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .vertical
        layout.minimumInteritemSpacing = 8
        layout.minimumLineSpacing = 16
        
        let collectionView = UICollectionView(frame: .zero, collectionViewLayout: layout)
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
        filterHotels()
        homeCollectionView.reloadData()
    }
    
    // MARK: - Filter Hotels by Stars
    private func filterHotels() {
        guard let hotelsResult = hotelsResult else { return }
        for hotel in hotelsResult {
            if hotel.stars == 1 {
                oneStarHotel.append(hotel)
            }
            else if hotel.stars == 2 {
                twoStarHotel.append(hotel)
            }
            else if hotel.stars == 3 {
                threeStarHotel.append(hotel)
            }
            else if hotel.stars == 4 {
                fourStarHotel.append(hotel)
            }
            else if hotel.stars == 5 {
                fiveStarHotel.append(hotel)
            }
            else {
                return
            }
        }
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
        /// Configure View.
        backgroundColor = .white
        /// Register CollectionView Cell.
        homeCollectionView.register(HomeCollectionViewCell.self, forCellWithReuseIdentifier: reuseIdentifier)
        homeCollectionView.register(CollectionViewHeaderView.self, forSupplementaryViewOfKind: UICollectionView.elementKindSectionHeader, withReuseIdentifier: headerIdentifier)
    }
}
