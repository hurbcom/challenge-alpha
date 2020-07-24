//
//  CardHorizontalCollectionViewCell.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 24/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import UIKit
import Kingfisher

class CardHorizontalCollectionViewCell: UICollectionViewCell {

    // MARK: Outlets
    @IBOutlet weak var vwCard: UIView!
    @IBOutlet weak var ivCard: UIImageView!
    @IBOutlet weak var lblTitle: UILabel!
    
    // MARK: Overrides
    override func awakeFromNib() {
        super.awakeFromNib()
        
        vwCard.layer.cornerRadius = 8
        vwCard.layer.shadowOffset = CGSize(width: 0, height: 2)
        vwCard.layer.shadowRadius = 3
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        ivCard.image = nil
        lblTitle.text = nil
    }

    // MARK: Setup
    func setup(with card: Highlights.Section.Card) {
        lblTitle.text = card.title
        
        if let imageUrl = card.imageUrl, let url = URL(string: imageUrl) {
            ivCard.kf.setImage(with: url)
        }
    }
}
