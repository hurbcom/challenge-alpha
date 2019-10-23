//
//  HotelDetailTableViewCell.swift
//  desafio-hurb-ios
//
//  Created by Vinícius Barcelos on 22/10/19.
//  Copyright © 2019 Vinícius Barcelos. All rights reserved.
//

import UIKit

class HotelDetailTableViewCell: UITableViewCell {
    
    @IBOutlet weak var hotelNameLabel: UILabel!
    @IBOutlet weak var hotelAdressLabel: UILabel!
    @IBOutlet weak var hotelPriceLabel: UILabel!
    @IBOutlet weak var hotelDescriptionLabel: UILabel!
    @IBOutlet weak var hotelStarsLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.customize()
    }

    func configCell(with hotel: Hotel) {
        self.hotelNameLabel.text = hotel.name
        self.hotelAdressLabel.text = hotel.fullAdress()
        self.hotelPriceLabel.text = hotel.price.inCurrency()
        self.hotelDescriptionLabel.text = hotel.smallDescription
        self.hotelStarsLabel.text = hotelStars(from: hotel)
    }
    
    func customize() {
        self.hotelPriceLabel.textColor = Theme.secondaryColor
        self.hotelAdressLabel.textColor = Theme.lightGrayColor
        self.hotelDescriptionLabel.textColor = Theme.lightGrayColor
    }
    
    func hotelStars(from hotel: Hotel) -> String {
        guard let stars = hotel.stars else { return "PACOTE" }
        var text: String = ""
        for _ in 1...stars {
            text.append("⭐")
        }
        return text
    }
}
