//
//  ListHotelCollectionViewCell.swift
//  desafio-hurb-ios
//
//  Created by Vinícius Barcelos on 21/10/19.
//  Copyright © 2019 Vinícius Barcelos. All rights reserved.
//

import UIKit

class ListHotelCollectionViewCell: UICollectionViewCell {

    @IBOutlet weak var hotelNameLabel: UILabel!
    @IBOutlet weak var hotelPriceLabel: UILabel!
    @IBOutlet weak var hotelCityLabel: UILabel!
    @IBOutlet weak var hotelImageView: UIImageView!
    @IBOutlet weak var containerView: UIView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.customize()
    }
    
    func customize() {
        self.roundedCorner(with: 8)
        self.containerView.backgroundColor = Theme.surfaceColor
        self.hotelPriceLabel.textColor = Theme.secondaryColor
    }

}
