//
//  FirebaseAnalytics.swift
//  Hurb
//
//  Created by Alexandre Papanis on 12/08/19.
//  Copyright © 2019 Papanis. All rights reserved.
//

import Foundation
import FirebaseAnalytics

class FirebaseAnalyticsHelper {
    
    static func viewHotelDetailsEventLogger(hotelName: String){
        
        Analytics.logEvent("viewDetails", parameters: [
            "hotelName": hotelName as NSObject
            ])
    }
    
    static func suggestionChosenEventoLoger(place: String){
        
        Analytics.logEvent("suggestionChosen", parameters: [
            "place": place as NSObject
            ])
    }
    
    static func isNotConnectedEventLogger(){
        Analytics.logEvent("isNotConnected", parameters: nil)
    }
}
