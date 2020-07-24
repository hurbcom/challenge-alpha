//
//  CardVerticalCollectionViewCell.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 24/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import UIKit

class CardVerticalCollectionViewCell: UICollectionViewCell {

    // MARK: Outlets
    @IBOutlet weak var vwCard: UIView!
    
    // MARK: Overrides
    override func awakeFromNib() {
        super.awakeFromNib()
        
        vwCard.layer.borderWidth = 0.2
        vwCard.layer.borderColor = UIColor.lightGray.cgColor
        vwCard.layer.cornerRadius = 8
    }
}
