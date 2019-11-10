//
//  FeedTableViewCell.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 09/11/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import UIKit
import SnapKit

class FeedTableViewCell: UITableViewCell {

    var currentDataSource: HotelsDataSource? {
        didSet {
            self.hotelsCollectionView.dataSource = currentDataSource
            self.hotelsCollectionView.delegate = self
            self.hotelsCollectionView.reloadData()
        }
    }
    
    var hotelsCollectionView: UICollectionView = {
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .horizontal
        layout.estimatedItemSize = CGSize(width: UIScreen.main.bounds.width,
                                          height: UIScreen.main.bounds.height/4)
        layout.minimumInteritemSpacing = 20
        layout.minimumLineSpacing = 20
        let view = UICollectionView(frame: .zero, collectionViewLayout: layout)
        view.showsHorizontalScrollIndicator = false
        view.backgroundColor = .clear
        view.contentInset = UIEdgeInsets(top: 0, left: 20, bottom: 0, right: 20)
        view.register(HotelCollectionViewCell.self,
                      forCellWithReuseIdentifier: "hotelsCollectionViewCell")
        return view
    }()
    
    func setupUI() {
        self.contentView.addSubview(hotelsCollectionView)
        self.hotelsCollectionView.collectionViewLayout.invalidateLayout()
        setupConstraints()
    }
    
    func setupConstraints() {
        hotelsCollectionView.snp.makeConstraints { (make) in
            make.top.equalToSuperview()
            make.left.equalToSuperview()
            make.right.equalToSuperview()
            make.bottom.equalToSuperview()
        }
    }
}

extension FeedTableViewCell: UICollectionViewDelegateFlowLayout {
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: self.contentView.bounds.size.width,
                      height: self.contentView.bounds.size.height)
    }
    
    
}
