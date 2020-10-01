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
        self.hotelName.text = hotelViewModel.hotelName
        self.cityName.text = hotelViewModel.hotelAddress
        
        self.setupView()
        self.setupFixedLabels()
       
    }
    
    func setupFixedLabels() {
        self.installmentsText.text = "+ Taxas | Em até 12x"
        
        
        self.cancelText.text = "Cancelamento Grátis"
    }
    
    func setupView() {
        self.backgroundColor = UIColor(red: 245.0/255.0, green: 245.0/255.0, blue: 245.0/255.0, alpha: 1.0)
        self.boxView.backgroundColor = .white
        self.boxView.layer.cornerRadius = 10
        self.hotelImage.layer.masksToBounds = true
    }
    
    func drawShadow() {
    //        layer.masksToBounds = false
        self.boxView.layer.shadowColor = UIColor.black.cgColor
        self.boxView.layer.shadowOpacity = 0.5
        self.boxView.layer.shadowOffset = CGSize(width: -0.7, height: 0.7)
        self.boxView.layer.shadowRadius = 5
    }
    
    func roundCornersInImage(corners: UIRectCorner) {
        let path = UIBezierPath(roundedRect:self.hotelImage.bounds, byRoundingCorners:corners, cornerRadii: CGSize(width: 10, height: 10))
        let maskLayer = CAShapeLayer()

        maskLayer.path = path.cgPath
        self.hotelImage.layer.mask = maskLayer
    }
    
}



