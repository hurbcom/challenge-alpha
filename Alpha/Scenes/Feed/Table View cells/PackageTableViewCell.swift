//
//  PackageTableViewCell.swift
//  Alpha
//
//  Created by Theo Mendes on 19/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import UIKit
import SnapKit

class PackageTableViewCell: BaseTableViewCell, UICollectionViewDelegate, UICollectionViewDelegateFlowLayout {
    // MARK: - Properties
    var screenWidth = UIScreen.main.bounds.width
    // Aspect Ratio is original height / width
    let imageAspec = (350 / 300)

    var currentDataSource: UICollectionViewDataSource? {
        didSet {
            self.collectionView.dataSource = currentDataSource
            self.collectionView.reloadData()
        }
    }

    var collectionView: UICollectionView = {
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .horizontal
        layout.sectionInset = UIEdgeInsets(top: 0, left: 0, bottom: 0, right: 0)
        layout.estimatedItemSize = CGSize(width: UIScreen.main.bounds.width, height: (UIScreen.main.bounds.width * (350 / 377)))
        let view = UICollectionView(frame: .zero, collectionViewLayout: layout)
        view.showsHorizontalScrollIndicator = false
        view.isPagingEnabled = true
        view.backgroundColor = .clear
        view.register(PackageCollectionViewCell.self,
                      forCellWithReuseIdentifier: Identifiers.Package.rawValue)
        return view
    }()

    // MARK: - View methods

    override func setupUI() {
        self.contentView.addSubview(collectionView)
    }

    override func setupConstraints() {
        collectionView.snp.makeConstraints { (make) in
            make.top.equalToSuperview()
            make.leading.trailing.equalToSuperview()
            make.height.equalTo(UIScreen.main.bounds.width * (350 / 377) + 5)
            make.bottom.equalToSuperview().priority(999)
        }
    }
}
