//
//  HotelHeaderViewCell.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 13/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import UIKit

class HotelHeaderViewCell: UICollectionViewCell, NibLoadable {
    
    static let defaultHeight = CGFloat(32)
    
    @IBOutlet private var iconView: UIImageView!
    
    override func prepareForReuse() {
        super.prepareForReuse()
        iconView.image = nil
    }
    
    func setup(hotelHeader: HotelHeaderDisplay) {
        if let icon = hotelHeader.icon {
            iconView.isHidden = false
            iconView.image = icon
            return
        }
    }

}
