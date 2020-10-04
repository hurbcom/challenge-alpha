//
//  HomePackageCellAccessibility+Extension.swift
//  Challenge-ios
//
//  Created by Andre Dias on 04/10/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import Foundation

// Configura imagem dos pacotes para uso na acessibilidade para a celula de pacotes

extension HomePackageTableViewCell {
    
    func applyAccessibility(_ package: HomePackagesTableCellViewModel) {
        self.applyPackageImageAccessibility(package)
     }
    
    private func applyPackageImageAccessibility(_ package: HomePackagesTableCellViewModel) {
        self.packageImage.isAccessibilityElement = true
        self.packageImage.accessibilityTraits = .image
        self.packageImage.accessibilityLabel = package.smallDescription
    }
    
}
