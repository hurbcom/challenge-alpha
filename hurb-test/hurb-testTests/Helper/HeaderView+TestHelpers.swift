//
//  HeaderView+TestHelpers.swift
//  hurb-testTests
//
//  Created by Tulio Parreiras on 20/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import HotelSearchiOS

extension HeaderView {
    
    var starsCount: Int {
        return self.stackView.arrangedSubviews.count
    }
    
    var isShowingNoneMessage: Bool {
        return self.label.superview != nil
    }
    
    var isShowingStars: Bool {
        return self.stackView.superview != nil
    }
    
}
