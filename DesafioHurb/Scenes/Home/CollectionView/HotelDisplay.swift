//
//  HotelDisplay.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 07/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import Foundation

struct HotelDisplay: Equatable {
    let id: String
    let image: URL?
    let city: String
    let name: String
    let originalAmountPerDay: NSAttributedString?
    let amountPerDay: String
    let condition: String
    let cancelationFree: String
    
    init(hotel: Hotel) {
        id = hotel.id
        if let imageUrl = hotel.image {
            self.image = URL(string: imageUrl)
        } else {
            image = nil
        }
        city = hotel.address.city
        name = hotel.name
        if let originalAmountPerDay = hotel.price.originalAmountPerDay {
            self.originalAmountPerDay = HotelDisplay.formatOriginalAmountPerDay(value: originalAmountPerDay)
        } else {
            self.originalAmountPerDay = NSAttributedString(string: " ")
        }
        amountPerDay = hotel.price.amountPerDay.currencyFormatted()
        condition = "em até 12x"
        cancelationFree = (hotel.huFreeCancellation ?? false) ? "Cancelamento grátis!":" "
    }
    
    private static func formatOriginalAmountPerDay(value: Double) -> NSAttributedString {
        let valueFormatted = value.currencyFormatted()
        let description = "Diárias de \(valueFormatted)"
        let attributedString = NSMutableAttributedString(string: description)
        let valueRange = description.count - valueFormatted.count
        attributedString.addAttribute(
            .strikethroughStyle,
            value: 2,
            range: NSRange(location: valueRange, length: valueFormatted.count)
        )
        
        return attributedString
    }
}
