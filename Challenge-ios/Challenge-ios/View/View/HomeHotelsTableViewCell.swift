//
//  HomeTableViewCell.swift
//  Challenge-ios
//
//  Created by Andre Dias on 30/09/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import UIKit
import Kingfisher

class HomeHotelsTableViewCell: UITableViewCell {
    
    //MARK: Variaveis
    @IBOutlet weak var amenitiesText: UILabel!
    @IBOutlet var starViewArray: [UIImageView]!
    @IBOutlet weak var cancelText: UILabel!
    @IBOutlet weak var installmentsText: UILabel!
    @IBOutlet weak var priceHotel: UILabel!
    @IBOutlet weak var boxView: UIView!
    @IBOutlet weak var hotelImage: UIImageView!
    @IBOutlet weak var hotelName: UILabel!
    @IBOutlet weak var cityName: UILabel!
    var homeHotelTableCellViewModel: HomeHotelTableCellViewModel! {
        didSet {
            self.setup(hotelViewModel: homeHotelTableCellViewModel)
        }
    }
    //MARK: Init
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
       
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        
        self.roundCornersInImage(corners: [.topLeft, .topRight])
        self.drawShadow()
    }
 
    //MARK: Métodos
    func setup(hotelViewModel: HomeHotelTableCellViewModel) {
        self.hotelImage.kf.setImage(with: hotelViewModel.hotelImageURL)
        self.setupName(string: hotelViewModel.hotelName)
        self.setupCityName(string: hotelViewModel.hotelAddress)
        self.setupStars(quantity: hotelViewModel.stars)
        self.setupPrice(string: hotelViewModel.hotelPrice)
        self.setupAmenities(string: hotelViewModel.amenities)
        self.setupView()
        self.setupFixedLabels()
        
        self.applyAccessibility(hotelViewModel)
        
      
    }
    
    // Lógica para setar hidden ou não nas imagens de estrelas para formar a imagem completa de estrelas dos hoteis
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
        self.hotelName.attributedText = GenericSingleton.shared.setAtributtedText(color: UIColor.black,
                                                                   font: UIFont(name:  Fonts.montserratBold, size: 15.0)!,
                                                                   string: string)
    }
    
    private func setupCityName(string: String) {
        self.cityName.attributedText = GenericSingleton.shared.setAtributtedText(color: UIColor.black,
                                                                                 font: UIFont(name: Fonts.montserratRegular, size: 13.0)!,
                                                                                 string: string)
    }
    
    private func setupAmenities(string: String) {
        self.amenitiesText.attributedText = GenericSingleton.shared.setAtributtedText(color: UIColor.black,
                                                                                      font: UIFont(name: Fonts.montserratRegular, size: 11.0)!,
                                                                                      string: string)
    }

    private func setupPrice(string: String) {
        self.priceHotel.attributedText = GenericSingleton.shared.setAtributtedText(color: Colors.orangePrice,
                                                                                   font: UIFont(name: Fonts.montserratBold, size: 18.0)!,
                                                                                   string: string)
    }
    
    private func setupFixedLabels() {
        self.installmentsText.attributedText = GenericSingleton.shared.setAtributtedText(color: UIColor.black,
                                                                                         font: UIFont(name: Fonts.montserratRegular, size: 11.0)!,
                                                                                         string: Constants.installmentsText)
        self.cancelText.attributedText = GenericSingleton.shared.setAtributtedText(color: Colors.greenCancel,
                                                                                   font: UIFont(name: Fonts.montserratRegular, size: 11.0)!,
                                                                                   string: Constants.freeCancelText)
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



