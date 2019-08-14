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
    
    static func viewHotelDetailsEventLogger(hotelName: Int){
        
        Analytics.logEvent("viewDetails", parameters: [
            "hotelName": hotelName as NSObject
            ])
    }
    
    static func isNotConnectedEventLogger(){
        Analytics.logEvent("isNotConnected", parameters: nil)
    }
}
