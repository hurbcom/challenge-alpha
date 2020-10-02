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
        let nameString = string
        let nameAttributes: [NSAttributedString.Key : Any] = [
            NSAttributedString.Key.foregroundColor: UIColor.black,
            NSAttributedString.Key.font: UIFont(name: Fonts.montserratRegular, size: 16.0)!]
        self.packageName.attributedText = NSAttributedString(string: nameString, attributes: nameAttributes)
    }
    
    private func setupCityName(string: String) {
        let cityString = string
        let cityAttributes: [NSAttributedString.Key : Any] = [
            NSAttributedString.Key.foregroundColor: UIColor.black,
            NSAttributedString.Key.font: UIFont(name: Fonts.montserratBold, size: 13.0)!]
        self.cityName.attributedText = NSAttributedString(string: cityString, attributes: cityAttributes)
    }
    
    private func setupSmallDescription(string: String) {
        let cityString = string
        let cityAttributes: [NSAttributedString.Key : Any] = [
            NSAttributedString.Key.foregroundColor: UIColor.black,
            NSAttributedString.Key.font: UIFont(name: Fonts.montserratRegular, size: 14.0)!]
        self.smallDescription.attributedText = NSAttributedString(string: cityString, attributes: cityAttributes)
    }
    
    private func setupAmenities(string: String) {
        let amenitiesString = string
        let amenitiesAttributes: [NSAttributedString.Key : Any] = [
            NSAttributedString.Key.foregroundColor: UIColor.black,
            NSAttributedString.Key.font: UIFont(name: Fonts.montserratRegular, size: 11.0)!]
        self.amenitiesText.attributedText = NSAttributedString(string: amenitiesString, attributes: amenitiesAttributes)
    }
    
    private func setupQuantityDescriptor(string: String) {
        let amenitiesString = string
        let amenitiesAttributes: [NSAttributedString.Key : Any] = [
            NSAttributedString.Key.foregroundColor: UIColor.black,
            NSAttributedString.Key.font: UIFont(name: Fonts.montserratRegular, size: 11.0)!]
        self.quantityDescriptor.attributedText = NSAttributedString(string: amenitiesString, attributes: amenitiesAttributes)
    }

    private func setupPrice(string: String) {
        let priceString = string
        let priceAttributes: [NSAttributedString.Key : Any] = [
            NSAttributedString.Key.foregroundColor: Colors.orangePrice,
            NSAttributedString.Key.font: UIFont(name: Fonts.montserratBold, size: 18.0)!]
        self.pricePackage.attributedText = NSAttributedString(string: priceString, attributes: priceAttributes)
    }
    
    private func setupFixedLabels() {
        let priceSupportString = Constants.priceSupportText
        let priceSupportAttributes: [NSAttributedString.Key : Any] = [
                   NSAttributedString.Key.font: UIFont(name: Fonts.montserratRegular, size: 11.0)!]
        self.priceSupportText.attributedText = NSAttributedString(string: priceSupportString, attributes: priceSupportAttributes)
        
        let cancelString = Constants.freeCancelText
        let cancelAttributes: [NSAttributedString.Key : Any] = [
            NSAttributedString.Key.foregroundColor: Colors.greenCancel,
            NSAttributedString.Key.font: UIFont(name: Fonts.montserratRegular, size: 11.0)!]
        self.cancelText.attributedText = NSAttributedString(string: cancelString, attributes: cancelAttributes)
        
        let taxFreeString = Constants.taxFreeText
        let taxFreeAttributes: [NSAttributedString.Key : Any] = [
            NSAttributedString.Key.foregroundColor: Colors.greenCancel,
            NSAttributedString.Key.font: UIFont(name: Fonts.montserratRegular, size: 11.0)!]
        self.taxFreeText.attributedText = NSAttributedString(string: taxFreeString, attributes: taxFreeAttributes)
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




