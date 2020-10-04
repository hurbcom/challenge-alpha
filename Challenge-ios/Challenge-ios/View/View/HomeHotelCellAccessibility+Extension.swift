//
//  HomeHotelCellAccessibility+Extension.swift
//  Challenge-ios
//
//  Created by Andre Dias on 04/10/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import Foundation

// Configura imagem e estrelas dos hoteis para uso na acessibilidade para a celula de hoteis

extension HomeHotelsTableViewCell {
    
    func applyAccessibility(_ hotel: HomeHotelTableCellViewModel) {
        self.applyHotelImageAccessibility(hotel)
        self.applyStarsAccessiblity(hotel)
        
     }
    
    private func applyHotelImageAccessibility(_ hotel: HomeHotelTableCellViewModel) {
        self.hotelImage.isAccessibilityElement = true
        self.hotelImage.accessibilityIdentifier = "hotelImage"
        self.hotelImage.accessibilityTraits = .image
        self.hotelImage.accessibilityLabel = hotel.smallDescription
    }
    
    private func applyStarsAccessiblity(_ hotel: HomeHotelTableCellViewModel) {
        self.starViewArray.first!.isAccessibilityElement = true
        self.starViewArray.first!.accessibilityTraits = .none
        
        self.starViewArray.first!.accessibilityLabel = "Estrelas"
        switch hotel.stars {
            case 3:
                self.starViewArray.first!.accessibilityValue = "Hotel 3 Estrelas"
            case 4:
                self.starViewArray.first!.accessibilityValue = "Hotel 4 Estrelas"
            case 5:
                self.starViewArray.first!.accessibilityValue = "Hotel 5 Estrelas"
            default:
                self.starViewArray.first!.accessibilityValue = "Hotel 3 Estrelas"
        }
    }
    
}
