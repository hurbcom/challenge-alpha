//
//  HomePackagesTableCellViewModel.swift
//  Challenge-ios
//
//  Created by Andre Dias on 02/10/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import Foundation

class HomePackagesTableCellViewModel {
    
    // MARK: Váriaveis
    var packageImageURL: URL?
    var packageName: String = ""
    var packageDestination: String = ""
    var packagePrice: String = ""
    var amenities: String = ""
    var quantityDescriptors: String = ""
    var smallDescription: String = ""

    // MARK: Init
    init(_ packageModel: PackageResults) {
        self.configPackageImage(model: packageModel)
        self.configPackageName(model: packageModel)
        self.configPackageDestination(model: packageModel)
        self.configPackagePrice(model: packageModel)
        self.configPackageAmenities(model: packageModel)
        self.configPackageQuantityDescriptor(model: packageModel)
        self.configPackageSmallDescription(model: packageModel)

    }
    
    // MARK: Métodos
    private func configPackageImage(model: PackageResults) {
        if let gallery = model.gallery {
            if let firstImage = gallery.first {
                if let url = URL(string: firstImage.url) {
                    self.packageImageURL = url
                }
            }
        }
    }
    
    private func configPackageName(model: PackageResults) {
        self.packageName = model.name
    }
    
    private func configPackageDestination(model: PackageResults) {
        if let address = model.address {
            self.packageDestination = "\(address.city) / \( address.state)"
        }
    }
    
    private func configPackagePrice(model: PackageResults) {
        if let price = model.price?.currentPrice {
            if let formattedValue = GenericSingleton.shared.currencyFormatter.string(from: price as NSNumber) {
                self.packagePrice = formattedValue
            }
        }
    }
    
    private func configPackageAmenities(model: PackageResults) {
        var ametiesString = ""
        if let amenities = model.amenities {
            for (index, element) in amenities.enumerated() {
                if index == 3 {
                    break
                } else {
                    ametiesString.append("\(element.name)\n")
                }
            }
            self.amenities = ametiesString
        }
        
    }
    
    private func configPackageQuantityDescriptor(model: PackageResults) {
        var nightsString = ""
        var maxPeopleString = ""
        
        if let quantityDescriptor = model.quantityDescriptors {
            if quantityDescriptor.nights == 1 {
                nightsString = "\(quantityDescriptor.nights) diária"
            } else {
                nightsString = "\(quantityDescriptor.nights) diárias"
            }
            if quantityDescriptor.maxPeople == 1 {
                maxPeopleString = "\(quantityDescriptor.maxPeople) pessoa"
            } else {
                maxPeopleString = "\(quantityDescriptor.maxPeople) pessoas"
            }
            
            self.quantityDescriptors = "\(nightsString), \(maxPeopleString)"
        }
    }
    
    private func configPackageSmallDescription(model: PackageResults) {
        self.smallDescription = model.smallDescription
     }
    
}


