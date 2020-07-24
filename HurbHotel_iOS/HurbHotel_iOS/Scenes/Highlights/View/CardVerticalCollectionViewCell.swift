//
//  CardVerticalCollectionViewCell.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 24/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import UIKit
import Kingfisher

class CardVerticalCollectionViewCell: UICollectionViewCell {

    // MARK: Outlets
    @IBOutlet weak var vwCard: UIView!
    @IBOutlet weak var ivCard: UIImageView!
    
    // MARK: Overrides
    override func awakeFromNib() {
        super.awakeFromNib()
        
        vwCard.layer.borderWidth = 0.2
        vwCard.layer.borderColor = UIColor.lightGray.cgColor
        vwCard.layer.cornerRadius = 8
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        
        ivCard.image = nil
    }
    
    // MARK: Setup
    func setup(with card: Highlights.Section.Card) {
        
        if let imageUrl = card.imageUrl, let url = URL(string: imageUrl) {
            ivCard.kf.setImage(with: url)
        }
    }
}
