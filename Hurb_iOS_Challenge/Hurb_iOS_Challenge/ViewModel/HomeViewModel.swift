//
//  HomeViewModel.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 24/05/22.
//

import UIKit
import SwiftUI
import MapKit

class HomeViewModel {
    // MARK: - Properties
    
    /// Hotel Result
    var hotelResult: HotelResult?
    
    var hotelsImage: [String] {
        var imagesArray: [String] = []
        guard let gallery = hotelResult?.gallery else { return [""] }
        for image in gallery {
            if var imageURL = image.url {
                if !imageURL.contains("https:") {
                    imageURL.insert("s", at: imageURL.index(imageURL.startIndex, offsetBy: 4))
                }
                imagesArray.append(imageURL)
            }
        }
        return imagesArray
    }
    
    var hotelDescriptionText: NSAttributedString {
        let cityAddress = City.gramado.rawValue //hotelsResult?.address?.city?.gramado
        let countryAddress = Country.brasil.rawValue //hotelsResult?.address?.country?.brasil
        let text = NSMutableAttributedString(string: hotelResult?.name ?? "--",
                                             attributes: [.font: UIFont.systemFont(ofSize: 13, weight: .semibold)])
        text.append(NSAttributedString(string: "・" + (cityAddress.capitalizedFirstLetter()) ,
                                       attributes: [.font: UIFont.systemFont(ofSize: 13, weight: .semibold)]))
        text.append(NSAttributedString(string: ", " + (countryAddress.capitalizedFirstLetter()), attributes: [.font: UIFont.systemFont(ofSize: 13, weight: .semibold)]))
        return text
    }
    
    var hotelFinalPriceText: NSAttributedString {
        guard let hotelPrice = hotelResult?.price?.currentPrice else { return NSAttributedString(string: "--") }
        guard let priceByNights = hotelResult?.price?.originalAmountPerDay else { return NSAttributedString(string: "--") }
        let price = CurrencyUtils.formatPrice(price: hotelPrice)
        var nights = 0
        if (Int(priceByNights) != 0) {
            nights = Int((Double(hotelPrice))/Double(priceByNights))
        }
        let text = NSMutableAttributedString(string: price,
                                             attributes: [.font: UIFont.systemFont(ofSize: 12, weight: .semibold)])
        if nights > 0 {
            text.append(NSAttributedString(string: " - \(String(nights))",
                                           attributes: [.font: UIFont.systemFont(ofSize: 12, weight: .regular)]))
            if nights > 1 {
                text.append(NSAttributedString(string: " diárias",
                                               attributes: [.font: UIFont.systemFont(ofSize: 12, weight: .regular)]))
            }
            else {
                text.append(NSAttributedString(string: " diária",
                                               attributes: [.font: UIFont.systemFont(ofSize: 12, weight: .regular)]))
            }
        }
        
        return text
    }
    
    var hotelAmenitiesText: NSAttributedString {
        guard let amenities = hotelResult?.amenities else { return NSAttributedString(string: "--") }
        var amenityArray: [String] = []
        
        for amenity in amenities {
            if let name = amenity.name {
                amenityArray.append(name)
            }
        }
        
        let firt3Amenities = amenityArray.prefix(3)
        
        let text = NSMutableAttributedString(string: firt3Amenities[0],
                                             attributes: [.font: UIFont.systemFont(ofSize: 12, weight: .regular)])
        text.append(NSAttributedString(string: " - \(firt3Amenities[1])",
                                       attributes: [.font: UIFont.systemFont(ofSize: 12, weight: .regular)]))
        text.append(NSAttributedString(string: " - \(firt3Amenities[2])",
                                       attributes: [.font: UIFont.systemFont(ofSize: 12, weight: .regular)]))
        
        return text
    }
    
    var freeCancelingText: String {
        if hotelResult?.huFreeCancellation ?? false {
            return "Cancelamento gratuito"
        }
        else {
            return ""
        }
    }
    
}
