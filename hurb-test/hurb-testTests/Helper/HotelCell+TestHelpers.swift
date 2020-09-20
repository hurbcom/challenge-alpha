//
//  HotelCell+TestHelpers.swift
//  hurb-testTests
//
//  Created by Tulio Parreiras on 20/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation
import HotelSearchiOS

extension HotelCell {
    
    var isShowingName: Bool {
        return self.lblName.text != nil
    }
    
    var isShowingLocation: Bool {
        return self.lblLocation.text != nil
    }
    
    var isShowingAmenities: Bool {
        return self.lblAmenities.text != nil
    }
    
    var isShowingPrice: Bool {
        return self.lblPrice.text != nil
    }
    
    var isShowingImageLoadingIndicator: Bool {
        return self.imageContainer.isShimmering
    }
    
    var renderedImage: Data? {
        return self.imvBackground.image?.pngData()
    }
    
}
