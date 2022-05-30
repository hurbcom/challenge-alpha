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
    ///
    /// Hotel Result: `Home`
    ///
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
                                             attributes: [.font: UIFont.systemFont(ofSize: 14, weight: .regular)])
        text.append(NSAttributedString(string: "・" + (cityAddress.capitalizedFirstLetter()) ,
                                       attributes: [.font: UIFont.systemFont(ofSize: 13, weight: .regular)]))
        text.append(NSAttributedString(string: ", " + (countryAddress.capitalizedFirstLetter()), attributes: [.font: UIFont.systemFont(ofSize: 13, weight: .regular)]))
        return text
    }
    
    var hotelFinalPriceText: NSAttributedString {
        guard let priceByNights = hotelResult?.price?.amountPerDay else { return NSAttributedString(string: "--") }
        let price = CurrencyUtils.formatPrice(price: priceByNights)
    
        let text = NSMutableAttributedString(string: price,
                                             attributes: [.font: UIFont.systemFont(ofSize: 13, weight: .regular)])
            text.append(NSAttributedString(string: " / noite",
                                           attributes: [.font: UIFont.systemFont(ofSize: 12, weight: .light)]))
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
        
        let text = NSMutableAttributedString(string: "✓ " + firt3Amenities[0],
                                             attributes: [.font: UIFont.systemFont(ofSize: 12, weight: .light)])
        text.append(NSAttributedString(string: " ✓ \(firt3Amenities[1])",
                                       attributes: [.font: UIFont.systemFont(ofSize: 12, weight: .light)]))
        text.append(NSAttributedString(string: " ✓ \(firt3Amenities[2])",
                                       attributes: [.font: UIFont.systemFont(ofSize: 12, weight: .light)]))
        
        return text
    }
    
    var hotelStars: String {
        var text = ""
        if let stars = hotelResult?.stars {
            text = String(stars)
            text += ".0"
            text = "★ " + text
        }
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
    
    ///
    /// Hotel Result:  `Details`
    ///
    var hotelDetailName: String {
        let text = hotelResult?.name ?? "--"
        return text
    }
    
    var hotelDetailLocation: NSAttributedString {
        let cityAddress = City.gramado.rawValue //hotelsResult?.address?.city?.gramado
        let countryAddress = Country.brasil.rawValue //hotelsResult?.address?.country?.brasil
        let text = NSMutableAttributedString(string: "・ " + cityAddress.capitalizedFirstLetter(),
                                             attributes: [.font: UIFont.systemFont(ofSize: 13, weight: .regular)])
        text.append(NSAttributedString(string: ", " + (countryAddress.capitalizedFirstLetter()), attributes: [.font: UIFont.systemFont(ofSize: 13, weight: .regular)]))
        return text
    }
    
    var hotelDetailSmallDescription: NSAttributedString {
        let text = NSMutableAttributedString(string: (hotelResult?.smallDescription ?? hotelResult?.resultDescription) ?? "--",
                                             attributes: [.font: UIFont.systemFont(ofSize: 13, weight: .light)])
        text.append(NSAttributedString(string: " ‣ Ver mais. ",
                                       attributes: [.font: UIFont.systemFont(ofSize: 12, weight: .regular), .foregroundColor: UIColor.hurbMainBlue]))
        return text
    }
    
    var hotelDetailFullDescription: String {
        let text = hotelResult?.resultDescription ?? "--"
        return text
    }
    
    var hotelDetailAmenitiesText: NSAttributedString {
        guard let amenities = hotelResult?.amenities else { return NSAttributedString(string: "--") }
        var amenityArray: [String] = []
        
        for amenity in amenities {
            if let name = amenity.name {
                amenityArray.append(name)
            }
        }
        
        let text = NSMutableAttributedString(string: "Comodidades: \n",
                                             attributes: [.font: UIFont.systemFont(ofSize: 12, weight: .semibold)])
        
        for i in 0...(amenityArray.count - 1) {
            text.append(NSAttributedString(string: " ✓ \(amenityArray[i])",
                                           attributes: [.font: UIFont.systemFont(ofSize: 12, weight: .light)]))
        }
        return text
    }
}
