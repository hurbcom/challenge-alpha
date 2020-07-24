//
//  HighlightVerticalCell.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 24/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import UIKit

class HighlightVerticalCell: UITableViewCell {

    // MARK: Outlets
    @IBOutlet weak var lblTitle: UILabel!
    @IBOutlet weak var lblSubtitle: UILabel!
    @IBOutlet weak var heightCollection: NSLayoutConstraint!
    @IBOutlet weak var collectionView: UICollectionView! {
        didSet{
            collectionView.register(UINib(nibName: CardVerticalCollectionViewCell().identifier, bundle: nil), forCellWithReuseIdentifier: CardVerticalCollectionViewCell().identifier)
            collectionView.dataSource = self
            collectionView.reloadData()
        }
    }
    
    // MARK: Overrides
    override func awakeFromNib() {
        super.awakeFromNib()
        selectionStyle = .none
        
        let layout = UICollectionViewFlowLayout()
        layout.minimumLineSpacing = 20
        let width = (UIScreen.main.bounds.width * 0.35)
        layout.itemSize = CGSize(width: width, height: 250)

        
        collectionView.isScrollEnabled = false
        collectionView.setCollectionViewLayout(layout, animated: true)
        collectionView.reloadData()
    }

    // MARK: Setup
    func setup() {
        let height: CGFloat = collectionView.collectionViewLayout.collectionViewContentSize.height
        heightCollection.constant = height
        collectionView.layoutIfNeeded()
        collectionView.reloadData()
    }
}

// MARK: Extensions
extension HighlightVerticalCell: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 8
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        if let cell = collectionView.dequeueReusableCell(withReuseIdentifier: CardVerticalCollectionViewCell().identifier, for: indexPath) as? CardVerticalCollectionViewCell {
            
            return cell
        }
        
        return UICollectionViewCell()
    }
}
