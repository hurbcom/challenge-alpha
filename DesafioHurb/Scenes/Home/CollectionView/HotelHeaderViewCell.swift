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
    
    @IBOutlet private var containerView: UIView!
    @IBOutlet private var titleLabel: UILabel!
    
    override func didMoveToWindow() {
        super.didMoveToWindow()
        containerView.layer.cornerRadius = 4.0
        containerView.clipsToBounds = true
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        titleLabel.text = ""
    }
    
    func setup(hotelHeader: HotelHeaderDisplay) {
        titleLabel.text = hotelHeader.title
    }
    
}
