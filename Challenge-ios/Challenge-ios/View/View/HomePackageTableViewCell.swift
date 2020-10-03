//
//  HomePackageTableViewCell.swift
//  Challenge-ios
//
//  Created by Andre Dias on 02/10/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import UIKit
import Kingfisher

class HomePackageTableViewCell: UITableViewCell {
    
    @IBOutlet weak var taxFreeText: UILabel!
    @IBOutlet weak var quantityDescriptor: UILabel!
    @IBOutlet weak var smallDescription: UILabel!
    @IBOutlet weak var amenitiesText: UILabel!
    @IBOutlet weak var cancelText: UILabel!
    @IBOutlet weak var priceSupportText: UILabel!
    @IBOutlet weak var pricePackage: UILabel!
    @IBOutlet weak var boxView: UIView!
    @IBOutlet weak var packageImage: UIImageView!
    @IBOutlet weak var packageName: UILabel!
    @IBOutlet weak var cityName: UILabel!
    var homePackagesTableCellViewModel: HomePackagesTableCellViewModel! {
        didSet {
            self.setup(packageViewModel: homePackagesTableCellViewModel)
        }
    }

    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
       
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        
        self.roundCornersInImage(corners: [.topLeft, .topRight])
        self.drawShadow()
    }
 
    func setup(packageViewModel: HomePackagesTableCellViewModel) {
        self.packageImage.kf.setImage(with: packageViewModel.packageImageURL)
        self.setupName(string: packageViewModel.packageName)
        self.setupCityName(string: packageViewModel.packageDestination)
        self.setupSmallDescription(string: packageViewModel.smallDescription)
        self.setupPrice(string: packageViewModel.packagePrice)
        self.setupAmenities(string: packageViewModel.amenities)
        self.setupQuantityDescriptor(string: packageViewModel.quantityDescriptors)
        self.setupView()
        self.setupFixedLabels()
        
      
    }

    private func setupName(string: String) {
        self.packageName.attributedText = GenericSingleton.shared.setAtributtedText(color: UIColor.black,
                                                                 font: UIFont(name: Fonts.montserratRegular, size: 16.0)!,
                                                                 string: string)
    }
    
    private func setupCityName(string: String) {
        self.cityName.attributedText = GenericSingleton.shared.setAtributtedText(color: UIColor.black,
                                                              font: UIFont(name: Fonts.montserratBold, size: 13.0)!,
                                                              string: string)
    }
    
    private func setupSmallDescription(string: String) {
        self.smallDescription.attributedText = GenericSingleton.shared.setAtributtedText(color: UIColor.black,
                                                                      font: UIFont(name: Fonts.montserratRegular, size: 14.0)!,
                                                                      string: string)
    }
    
    private func setupAmenities(string: String) {
        self.amenitiesText.attributedText = GenericSingleton.shared.setAtributtedText(color: UIColor.black,
                                                                   font: UIFont(name: Fonts.montserratRegular, size: 12.0)!,
                                                                   string: string)
    }
    
    private func setupQuantityDescriptor(string: String) {
        self.quantityDescriptor.attributedText = GenericSingleton.shared.setAtributtedText(color: UIColor.black,
                                                                        font: UIFont(name: Fonts.montserratRegular, size: 12.0)!,
                                                                        string: string)
    }

    private func setupPrice(string: String) {
        self.pricePackage.attributedText = GenericSingleton.shared.setAtributtedText(color: Colors.orangePrice,
                                                                  font: UIFont(name: Fonts.montserratBold, size: 18.0)!,
                                                                  string: string)
    }
    
    private func setupFixedLabels() {
        self.priceSupportText.attributedText = GenericSingleton.shared.setAtributtedText(color: UIColor.black,
                                                                      font: UIFont(name: Fonts.montserratRegular, size: 12.0)!,
                                                                      string: Constants.priceSupportText)
       
        self.cancelText.attributedText = GenericSingleton.shared.setAtributtedText(color: Colors.greenCancel,
                                                                font: UIFont(name: Fonts.montserratRegular, size: 12.0)!,
                                                                string: Constants.freeCancelText)
        
        self.taxFreeText.attributedText = GenericSingleton.shared.setAtributtedText(color: Colors.greenCancel,
                                                                font: UIFont(name: Fonts.montserratRegular, size: 12.0)!,
                                                                string: Constants.taxFreeText)
    }
    
    private func setupView() {
        self.backgroundColor = Colors.backgroundGray
        self.boxView.backgroundColor = .white
        self.boxView.layer.cornerRadius = 10
        self.packageImage.layer.masksToBounds = true
    }
    
    private func drawShadow() {
        self.boxView.layer.shadowColor = UIColor.black.cgColor
        self.boxView.layer.shadowOpacity = 0.3
        self.boxView.layer.shadowOffset = CGSize(width: -0.3, height: 0.3)
        self.boxView.layer.shadowRadius = 5
    }
    
    private func roundCornersInImage(corners: UIRectCorner) {
        let path = UIBezierPath(roundedRect:self.packageImage.bounds, byRoundingCorners:corners, cornerRadii: CGSize(width: 10, height: 10))
        let maskLayer = CAShapeLayer()

        maskLayer.path = path.cgPath
        self.packageImage.layer.mask = maskLayer
    }
    
}




