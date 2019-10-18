//
//  HotelTableViewCell.swift
//  Alpha
//
//  Created by Theo Mendes on 18/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import UIKit
import SnapKit

class HotelTableViewCell: BaseTableViewCell {
    var currentDataSource: UICollectionViewDataSource? {
        didSet {
            self.collectionView.dataSource = currentDataSource
            self.collectionView.reloadData()
        }
    }
    
    var collectionView: UICollectionView = {
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .horizontal
        layout.itemSize = CGSize(width: 153, height: 263)
        let view = UICollectionView(frame: .zero, collectionViewLayout: layout)
        view.showsHorizontalScrollIndicator = false
        view.backgroundColor = .clear
        return view
    }()
    
    override func setupUI() {
        self.contentView.addSubview(collectionView)
    }

    override func setupConstraints() {
        collectionView.snp.makeConstraints { (make) in
            make.leading.trailing.top.equalToSuperview()
            make.bottom.equalToSuperview().offset(-20.0)
            make.height.equalTo(263).priority(999)
        }
    }
}
