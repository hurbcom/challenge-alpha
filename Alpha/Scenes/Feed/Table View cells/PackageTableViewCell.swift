//
//  PackageTableViewCell.swift
//  Alpha
//
//  Created by Theo Mendes on 19/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import UIKit

import UIKit
import SnapKit

class PackageTableViewCell: BaseTableViewCell, UICollectionViewDelegate, UICollectionViewDelegateFlowLayout {
    var currentDataSource: UICollectionViewDataSource? {
        didSet {
            guard let currentDataSource = currentDataSource as? PackageDataSource else { return }
            self.collectionView.dataSource = currentDataSource
            self.pageControl.numberOfPages = currentDataSource.items.count
            self.collectionView.reloadData()
        }
    }

    var collectionView: UICollectionView = {
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .horizontal
        layout.estimatedItemSize = CGSize(width: 340, height: 254)
        layout.minimumInteritemSpacing = 20
        layout.minimumLineSpacing = 20
        let view = UICollectionView(frame: .zero, collectionViewLayout: layout)
        view.showsHorizontalScrollIndicator = false
        view.contentInset = UIEdgeInsets(top: 0, left: 20, bottom: 0, right: 20)
        view.backgroundColor = .clear
        view.register(PackageCollectionViewCell.self, forCellWithReuseIdentifier: Identifiers.Package.rawValue)
        view.isPagingEnabled = true
        return view
    }()

    private lazy var pageControl: UIPageControl = {
        let page = UIPageControl(frame: .zero)
        return page
    }()

    func scrollViewDidEndDecelerating(_ scrollView: UIScrollView) {

        pageControl.currentPage = Int(scrollView.contentOffset.x) / Int(scrollView.frame.width)
    }

    func scrollViewDidEndScrollingAnimation(_ scrollView: UIScrollView) {

        pageControl.currentPage = Int(scrollView.contentOffset.x) / Int(scrollView.frame.width)
    }

    override func setupUI() {
        collectionView.delegate = self
        self.contentView.addSubview(collectionView)
        self.contentView.addSubview(pageControl)
    }

    override func setupConstraints() {
        collectionView.snp.makeConstraints { (make) in
            make.top.equalToSuperview()
            make.leading.trailing.equalToSuperview()
            make.bottom.equalToSuperview().offset(-20.0)
            make.height.equalTo(280).priority(999)
        }
        pageControl.snp.makeConstraints { (make) in
            make.top.equalTo(collectionView.snp.bottom)
            make.leading.trailing.equalTo(collectionView)
            make.bottom.equalToSuperview().priority(999)
        }
    }
}
