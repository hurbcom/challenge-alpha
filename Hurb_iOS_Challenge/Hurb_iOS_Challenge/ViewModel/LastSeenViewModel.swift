//
//  LastSeenViewModel.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 02/06/22.
//

import UIKit

class LastSeenViewModel {
    ///
    ///  LastSeen Hotels Local Result.
    ///
    var hotelResult: LastSeenHotel?
    
    var hotelNameText: String {
        let text = hotelResult?.name ?? "--"
        return text
    }
    
    var hotelSmallDescriptionText: String {
        let text = hotelResult?.smallDescription ?? "--"
        return text
    }
    
    var hotelImageURL: URL {
        let url = URL(string: hotelResult?.image ?? "")!
        return url
    }
    
    var hotelStars: String {
        var text = "★ --"
        if let stars = hotelResult?.stars {
            text = String(stars)
            text += ".0"
            text = "★ " + text
        }
        return text
    }
}

