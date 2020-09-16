//
//  HotelDisplay.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 07/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import UIKit
import Foundation

struct HotelDisplay: Equatable {
    let id: String
    let image: URL?
    let address: String
    let name: String
    let stars: Int?
    let originalAmountPerDay: NSAttributedString?
    let amountPerDay: String
    let condition: String
    let cancelationFree: String
    let amenities: [String ]
    
    var ratingImage: UIImage {
        guard let stars = stars else { return UIImage() }
        switch stars {
        case 1:
            return UIImage(named: "ic_star_one")!
        case 2:
            return UIImage(named: "ic_star_two")!
        case 3:
            return UIImage(named: "ic_star_three")!
        case 4:
            return UIImage(named: "ic_star_four")!
        case 5:
            return UIImage(named: "ic_star_five")!
        default:
            return UIImage()
        }
    }
    
    init(hotel: Hotel) {
        id = hotel.id
        if let imageUrl = hotel.image {
            self.image = URL(string: imageUrl)
        } else {
            image = nil
        }
        address = "\(hotel.address.city)/\(hotel.address.state)"
        name = hotel.name
        stars = hotel.stars
        if let originalAmountPerDay = hotel.price.originalAmountPerDay {
            self.originalAmountPerDay = HotelDisplay.formatOriginalAmountPerDay(value: originalAmountPerDay)
        } else {
            self.originalAmountPerDay = NSAttributedString(string: " ")
        }
        amountPerDay = hotel.price.amountPerDay.currencyFormatted()
        condition = "em até 12x"
        cancelationFree = (hotel.huFreeCancellation ?? false) ? "Cancelamento grátis!":" "
        amenities = hotel.amenities.prefix(3).map { $0.name }
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
