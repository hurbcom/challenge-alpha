//
//  HighlightHorizontalCell.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 24/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import UIKit

class HighlightHorizontalCell: UITableViewCell {
    
    // MARK: Properties
    private var cards: [Highlights.Section.Card] = []

    // MARK: Outlets
    @IBOutlet weak var lblTitle: UILabel!
    @IBOutlet weak var lblSubtitle: UILabel!
    @IBOutlet weak var collectionView: UICollectionView! {
        didSet{
            collectionView.register(UINib(nibName: CardHorizontalCollectionViewCell().identifier, bundle: nil), forCellWithReuseIdentifier: CardHorizontalCollectionViewCell().identifier)
            collectionView.dataSource = self
            collectionView.delegate = self
            collectionView.reloadData()
        }
    }
    
    // MARK: Overrides
    override func awakeFromNib() {
        super.awakeFromNib()
        selectionStyle = .none
        
        let layout = SnappingLayout()
        layout.scrollDirection = .horizontal
        collectionView.setCollectionViewLayout(layout, animated: true)
        collectionView.contentInset = UIEdgeInsets(top: 0, left: 20, bottom: 0, right: 20)
        collectionView.decelerationRate = .fast
        
        collectionView.reloadData()
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        
        lblTitle.text = nil
        lblSubtitle.text = nil
        cards = []
    }
    
    // MARK: Setup
    func setup(with section: Highlights.Section) {
        lblTitle.text = section.title
        lblSubtitle.text = section.subtitle
        cards = section.cards ?? []
        collectionView.reloadData()
    }
}

// MARK: Extensions
extension HighlightHorizontalCell: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return cards.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let card = cards[indexPath.item]
        
        if let cell = collectionView.dequeueReusableCell(withReuseIdentifier: CardHorizontalCollectionViewCell().identifier, for: indexPath) as? CardHorizontalCollectionViewCell {
            cell.setup(with: card)
            return cell
        }
        return UICollectionViewCell()
    }
}

extension HighlightHorizontalCell: UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return .init(width: bounds.width - 40, height: 150)
    }
}
