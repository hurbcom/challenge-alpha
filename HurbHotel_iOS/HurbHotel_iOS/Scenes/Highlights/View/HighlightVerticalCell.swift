//
//  HighlightVerticalCell.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 24/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import UIKit

class HighlightVerticalCell: UITableViewCell {
    
    // MARK: Properties
    private var cards: [Highlights.Section.Card] = []
    var onClickCard: ((Highlights.Section.Card) -> ())?

    // MARK: Outlets
    @IBOutlet weak var lblTitle: UILabel!
    @IBOutlet weak var lblSubtitle: UILabel!
    @IBOutlet weak var heightCollection: NSLayoutConstraint!
    @IBOutlet weak var collectionView: UICollectionView! {
        didSet{
            collectionView.register(UINib(nibName: CardVerticalCollectionViewCell().identifier, bundle: nil), forCellWithReuseIdentifier: CardVerticalCollectionViewCell().identifier)
            collectionView.dataSource = self
            collectionView.delegate = self
            collectionView.reloadData()
        }
    }
    
    // MARK: Overrides
    override func awakeFromNib() {
        super.awakeFromNib()
        selectionStyle = .none
        
        let layout = UICollectionViewFlowLayout()
        layout.minimumLineSpacing = 20
        let width = (UIScreen.main.bounds.width * 0.40)
        layout.itemSize = CGSize(width: width, height: 250)

        collectionView.setCollectionViewLayout(layout, animated: true)
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
        
        ///Calculando a altura da collectionView, com base no numero de Cards.
        var numberLines: Int = cards.count / 2
        numberLines += (cards.count % 2) == 0 ? 0 : 1
        let heightForLine = 270
        heightCollection.constant = CGFloat(numberLines * heightForLine)
        collectionView.layoutIfNeeded()
        collectionView.reloadData()
    }
}

// MARK: Extensions
extension HighlightVerticalCell: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return cards.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let card = cards[indexPath.item]
        
        if let cell = collectionView.dequeueReusableCell(withReuseIdentifier: CardVerticalCollectionViewCell().identifier, for: indexPath) as? CardVerticalCollectionViewCell {
            cell.setup(with: card)
            return cell
        }
        
        return UICollectionViewCell()
    }
}

extension HighlightVerticalCell: UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        let card = cards[indexPath.item]
        onClickCard?(card)
    }
}
