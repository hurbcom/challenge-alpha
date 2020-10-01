//
//  HomeTableViewCell.swift
//  Challenge-ios
//
//  Created by Andre Dias on 30/09/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import UIKit
import Kingfisher

class HomeTableViewCell: UITableViewCell {
    
    @IBOutlet weak var amenitiesText: UILabel!
    @IBOutlet var starViewArray: [UIImageView]!
    @IBOutlet weak var cancelText: UILabel!
    @IBOutlet weak var installmentsText: UILabel!
    @IBOutlet weak var priceHotel: UILabel!
    @IBOutlet weak var boxView: UIView!
    @IBOutlet weak var hotelImage: UIImageView!
    @IBOutlet weak var hotelName: UILabel!
    @IBOutlet weak var cityName: UILabel!
    var homeTableViewCellViewModel: HomeTableCellViewModel! {
        didSet {
            self.setup(hotelViewModel: homeTableViewCellViewModel)
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
 
    func setup(hotelViewModel: HomeTableCellViewModel) {
        self.hotelImage.kf.setImage(with: hotelViewModel.hotelImageURL)
        self.setupName(string: hotelViewModel.hotelName)
        self.setupCityName(string: hotelViewModel.hotelAddress)
        self.setupStars(quantity: hotelViewModel.stars)
        self.setupPrice(string: hotelViewModel.hotelPrice)
        self.setupAmenities(string: hotelViewModel.amenities)
        self.setupView()
        self.setupFixedLabels()
        
      
    }
    
    private func setupStars(quantity: Int) {
        for (index, element) in self.starViewArray.enumerated() {
            if index >= quantity {
                element.isHidden = true
            } else {
                element.isHidden = false
            }
        }
    }
    
    private func setupName(string: String) {
        let nameString = string
        let nameAttributes: [NSAttributedString.Key : Any] = [
            NSAttributedString.Key.foregroundColor: UIColor.black,
            NSAttributedString.Key.font: UIFont(name: Fonts.montserratBold, size: 15.0)!]
        self.hotelName.attributedText = NSAttributedString(string: nameString, attributes: nameAttributes)
    }
    
    private func setupCityName(string: String) {
        let cityString = string
        let cityAttributes: [NSAttributedString.Key : Any] = [
            NSAttributedString.Key.foregroundColor: UIColor.black,
            NSAttributedString.Key.font: UIFont(name: Fonts.montserratRegular, size: 13.0)!]
        self.cityName.attributedText = NSAttributedString(string: cityString, attributes: cityAttributes)
    }
    
    private func setupAmenities(string: String) {
        let amenitiesString = string
        let amenitiesAttributes: [NSAttributedString.Key : Any] = [
            NSAttributedString.Key.foregroundColor: UIColor.black,
            NSAttributedString.Key.font: UIFont(name: Fonts.montserratRegular, size: 11.0)!]
        self.amenitiesText.attributedText = NSAttributedString(string: amenitiesString, attributes: amenitiesAttributes)
    }

    private func setupPrice(string: String) {
        let priceString = string
        let priceAttributes: [NSAttributedString.Key : Any] = [
            NSAttributedString.Key.foregroundColor: Colors.orangePrice,
            NSAttributedString.Key.font: UIFont(name: Fonts.montserratBold, size: 18.0)!]
        self.priceHotel.attributedText = NSAttributedString(string: priceString, attributes: priceAttributes)
    }
    
    private func setupFixedLabels() {
        let installmentsString = Constants.installmentsText
        let installmentsAttributes: [NSAttributedString.Key : Any] = [
                   NSAttributedString.Key.font: UIFont(name: Fonts.montserratRegular, size: 11.0)!]
        self.installmentsText.attributedText = NSAttributedString(string: installmentsString, attributes: installmentsAttributes)
        
        let cancelString = Constants.freeCancelText
        let cancelAttributes: [NSAttributedString.Key : Any] = [
            NSAttributedString.Key.foregroundColor: Colors.greenCancel,
            NSAttributedString.Key.font: UIFont(name: Fonts.montserratRegular, size: 11.0)!]
        self.cancelText.attributedText = NSAttributedString(string: cancelString, attributes: cancelAttributes)
    }
    
    private func setupView() {
        self.backgroundColor = Colors.backgroundGray
        self.boxView.backgroundColor = .white
        self.boxView.layer.cornerRadius = 10
        self.hotelImage.layer.masksToBounds = true
    }
    
    private func drawShadow() {
        self.boxView.layer.shadowColor = UIColor.black.cgColor
        self.boxView.layer.shadowOpacity = 0.3
        self.boxView.layer.shadowOffset = CGSize(width: -0.3, height: 0.3)
        self.boxView.layer.shadowRadius = 5
    }
    
    private func roundCornersInImage(corners: UIRectCorner) {
        let path = UIBezierPath(roundedRect:self.hotelImage.bounds, byRoundingCorners:corners, cornerRadii: CGSize(width: 10, height: 10))
        let maskLayer = CAShapeLayer()

        maskLayer.path = path.cgPath
        self.hotelImage.layer.mask = maskLayer
    }
    
}



