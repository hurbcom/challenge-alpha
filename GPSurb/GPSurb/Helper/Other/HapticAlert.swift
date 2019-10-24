//
//  HapticAlert.swift
//  GPSurb
//
//  Created by Gilson Santos on 23/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import UIKit

class HapticAlert {
    class func hapticReturn(style: UIImpactFeedbackGenerator.FeedbackStyle) {
        let generator = UIImpactFeedbackGenerator(style: style)
        generator.prepare()
        generator.impactOccurred()
    }
}
