//
//  HotelDetailDisplay.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 08/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import UIKit

struct HotelDetailDisplay: Equatable {
    let id: String
    let image: URL?
    let city: String
    let name: String
    let stars: Int?
    let smallDescription: String
    let resultDescription: String
    let originalAmountPerDay: NSAttributedString?
    let amountPerDay: String
    let condition: String
    let cancelationFree: String
    let gallery: [Gallery]
    
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
        city = hotel.address.city
        name = hotel.name
        stars = hotel.stars
        smallDescription = hotel.smallDescription
        resultDescription = hotel.resultDescription
        if let originalAmountPerDay = hotel.price.originalAmountPerDay {
            self.originalAmountPerDay = HotelDetailDisplay.formatOriginalAmountPerDay(value: originalAmountPerDay)
        } else {
            self.originalAmountPerDay = NSAttributedString(string: " ")
        }
        amountPerDay = hotel.price.amountPerDay.currencyFormatted()
        condition = "+ Taxas em até 12x"
        cancelationFree = (hotel.huFreeCancellation ?? false) ? "Cancelamento grátis!":" "
        gallery = hotel.gallery
    }
    
    private static func formatOriginalAmountPerDay(value: Double) -> NSAttributedString {
        let description = value.currencyFormatted()
        let attributedString = NSMutableAttributedString(string: description)
        attributedString.addAttribute(
            .strikethroughStyle,
            value: 2,
            range: NSRange(location: 0, length: description.count)
        )
        
        return attributedString
    }
}
