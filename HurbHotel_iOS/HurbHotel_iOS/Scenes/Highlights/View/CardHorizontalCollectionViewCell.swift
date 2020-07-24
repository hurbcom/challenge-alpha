//
//  CardHorizontalCollectionViewCell.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 24/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import UIKit

class CardHorizontalCollectionViewCell: UICollectionViewCell {

    @IBOutlet weak var vwCard: UIView!
    @IBOutlet weak var ivCard: UIImageView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        vwCard.layer.cornerRadius = 8
    }

}
